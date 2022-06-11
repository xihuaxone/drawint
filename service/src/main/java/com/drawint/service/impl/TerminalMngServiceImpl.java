package com.drawint.service.impl;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.dal.mapper.TerminalActionMapper;
import com.drawint.dal.mapper.TerminalMQTTMapper;
import com.drawint.dal.mapper.UserTerminalActionPermissionMapper;
import com.drawint.domain.bo.*;
import com.drawint.domain.converter.TerminalConverter;
import com.drawint.domain.entity.*;
import com.drawint.domain.enums.TerminalActionPermissionLevelEnum;
import com.drawint.domain.enums.TerminalTypeEnum;
import com.drawint.service.TerminalMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerminalMngServiceImpl implements TerminalMngService {
    @Autowired
    private TerminalMQTTMapper terminalMQTTMapper;

    @Autowired
    private TerminalActionMapper terminalActionMapper;

    @Autowired
    private UserTerminalActionPermissionMapper permissionMapper;

    @Override
    public void registerTerminal(TerminalRegisterBO terminalRegisterBO, Long uid) {
        TerminalMQTT terminalMQTT = TerminalConverter.INSTANCE.terminalRegisterBO2TerminalMQTT(terminalRegisterBO);
        if (!TerminalTypeEnum.contains(terminalMQTT.getType())) {
            throw new BizException(BizExceptionType.TERMINAL_TYPE_NOT_EXISTS);
        }
        if (checkTerminalExists(terminalMQTT.getTopic())) {
            throw new BizException(BizExceptionType.TERMINAL_EXISTS);
        }
        terminalMQTTMapper.insertSelective(terminalMQTT);

        TerminalMQTT terminal = getByTopic(terminalRegisterBO.getTopic());
        if (terminal == null) {
            throw new BizException(BizExceptionType.TERMINAL_REGISTER_FAILED, "DB terminal record creating failed.");
        }
        registerAction(terminal.getId(), terminalRegisterBO.getActionList(), uid);
    }

    @Override
    public void registerAction(Long tmId, List<ActionRegisterBO> actionRegisterBOList, Long uid) {
        if (!checkTerminalExists(tmId)) {
            throw new BizException(BizExceptionType.TERMINAL_NOT_EXISTS);
        }

        for (ActionRegisterBO actionRegisterBO : actionRegisterBOList) {
            // 注册action;
            TerminalAction action = TerminalConverter.INSTANCE.actionRegisterBO2TerminalAction(actionRegisterBO);
            action.setTmId(tmId);
            terminalActionMapper.insertSelective(action);
            TerminalAction terminalAction = getAction(tmId, actionRegisterBO.getCode());
            if (terminalAction == null) {
                throw new BizException(BizExceptionType.TERMINAL_REGISTER_FAILED,
                        "DB terminal action record creating failed.");
            }
            // 注册用户对action的权限等级;
            UserTerminalActionPermission permission = new UserTerminalActionPermission();
            permission.setUid(uid);
            permission.setTmId(tmId);
            permission.setTaId(terminalAction.getId());
            permission.setPermissionLevel(actionRegisterBO.getUserPermissionLevel());
            permissionMapper.insertSelective(permission);
        }
    }

    @Override
    public List<TerminalBO> list(Long uid) {
        UserTerminalActionPermissionExample permissionExample = new UserTerminalActionPermissionExample();
        // 只查询该用户有权限(至少有可见权限)的终端和action;
        permissionExample.createCriteria().andUidEqualTo(uid);
        List<UserTerminalActionPermission> permissionList =
                permissionMapper.selectByExample(permissionExample).stream()
                .filter(rec -> TerminalActionPermissionLevelEnum.isVisible(rec.getPermissionLevel()))
                .collect(Collectors.toList());

        HashMap<Long, TerminalBO> terminalMap = new HashMap<>(5);
        for (UserTerminalActionPermission record : permissionList) {
            Long tmId = record.getTmId();
            Long taId = record.getTaId();
            TerminalMQTT terminalMQTT = terminalMQTTMapper.selectByPrimaryKey(tmId);
            TerminalAction terminalAction = terminalActionMapper.selectByPrimaryKey(taId);
            if (!terminalMap.containsKey(tmId)) {
                TerminalBO terminalBO = new TerminalBO(terminalMQTT);
                terminalMap.put(tmId, terminalBO);
            }
            terminalMap.get(tmId).addAction(TerminalConverter.INSTANCE.terminalAction2TerminalActionBO(terminalAction));
        }
        return new ArrayList<>(terminalMap.values());
    }

    @Override
    public TerminalBO get(String topic, Long uid) {
        TerminalMQTTExample example = new TerminalMQTTExample();
        example.createCriteria()
                .andTopicEqualTo(topic)
                .andIsDeletedEqualTo(false);
        TerminalMQTT terminal = getByTopic(topic);
        return get(terminal, uid);
    }

    @Override
    public TerminalBO get(Long tmId, Long uid) {
        return get(terminalMQTTMapper.selectByPrimaryKey(tmId), uid);
    }

    private TerminalBO get(TerminalMQTT terminal, Long uid) {
        if (terminal == null || terminal.getIsDeleted()) {
            return null;
        }
        TerminalBO terminalBO = new TerminalBO(terminal);

        UserTerminalActionPermissionExample permissionExample = new UserTerminalActionPermissionExample();
        permissionExample.createCriteria()
                .andUidEqualTo(uid)
                .andTmIdEqualTo(terminal.getId())
                .andPermissionLevelLessThanOrEqualTo(TerminalActionPermissionLevelEnum.VISIBLE.getId());
        List<UserTerminalActionPermission> permissionList = permissionMapper.selectByExample(permissionExample);

        for (UserTerminalActionPermission permission : permissionList) {
            TerminalAction terminalAction = terminalActionMapper.selectByPrimaryKey(permission.getTaId());
            if (terminalAction.getIsDeleted()) {
                continue;
            }
            TerminalActionBO actionBO = TerminalConverter.INSTANCE.terminalAction2TerminalActionBO(terminalAction);
            UserTerminalActionPermissionBO permissionBO = new UserTerminalActionPermissionBO();
            permissionBO.setPermissionLevel(permission.getPermissionLevel());
            actionBO.setPermission(permissionBO);
            terminalBO.addAction(actionBO);
        }
        return terminalBO;
    }

    @Override
    public UserTerminalActionPermission getActionPermission(Long uid, Long tmId, String actionCode) {
        TerminalAction action = getAction(tmId, actionCode);
        if (action == null) {
            throw new BizException(BizExceptionType.TERMINAL_ACTION_NOT_EXISTS,
                    "terminal: " + tmId + " action: " +actionCode);
        }
        UserTerminalActionPermissionExample permissionExample = new UserTerminalActionPermissionExample();
        permissionExample.createCriteria()
                .andUidEqualTo(uid)
                .andTmIdEqualTo(tmId)
                .andTaIdEqualTo(action.getId());
        List<UserTerminalActionPermission> permissionList = permissionMapper.selectByExample(permissionExample);
        if (permissionList.isEmpty()) {
            return null;
        }
        return permissionList.get(0);
    }

    private TerminalMQTT getByTopic(String topic) {
        TerminalMQTTExample example = new TerminalMQTTExample();
        example.createCriteria()
                .andTopicEqualTo(topic).andIsDeletedEqualTo(false);
        List<TerminalMQTT> records = terminalMQTTMapper.selectByExample(example);
        if (records.size() > 1) {
            throw new BizException(BizExceptionType.UNKNOWN_EXCEPTION,
                    String.format("terminal (%s) count = %s", topic, records.size()));
        }
        return records.isEmpty() ? null : records.get(0);
    }

    private TerminalAction getAction(Long tmId, String code) {
        TerminalActionExample example = new TerminalActionExample();
        example.createCriteria()
                .andTmIdEqualTo(tmId)
                .andCodeEqualTo(code)
                .andIsDeletedEqualTo(false);
        List<TerminalAction> records = terminalActionMapper.selectByExample(example);
        if (records.size() > 1) {
            throw new BizException(BizExceptionType.UNKNOWN_EXCEPTION,
                    String.format("terminal (%s) action (%s) count = %s", tmId, code, records.size()));
        }
        return records.isEmpty() ? null : records.get(0);
    }

    private boolean checkTerminalExists(String topic) {
        TerminalMQTTExample example = new TerminalMQTTExample();
        TerminalMQTTExample.Criteria criteria = example.createCriteria();
        criteria.andTopicEqualTo(topic);
        if (terminalMQTTMapper.countByExample(example) > 0) {
            return true;
        }
        return false;
    }

    private boolean checkTerminalExists(Long tmId) {
        if (terminalMQTTMapper.selectByPrimaryKey(tmId) != null) {
            return true;
        }
        return false;
    }
}
