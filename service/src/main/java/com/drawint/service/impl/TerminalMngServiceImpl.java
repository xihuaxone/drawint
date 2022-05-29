package com.drawint.service.impl;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.dal.mapper.TerminalActionMapper;
import com.drawint.dal.mapper.TerminalMQTTMapper;
import com.drawint.dal.mapper.UserTerminalActionPermissionMapper;
import com.drawint.domain.bo.ActionRegisterBO;
import com.drawint.domain.bo.TerminalBO;
import com.drawint.domain.bo.TerminalRegisterBO;
import com.drawint.domain.converter.TerminalConverter;
import com.drawint.domain.entity.*;
import com.drawint.domain.enums.TerminalActionPermissionLevelEnum;
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
    public void registerTerminal(TerminalRegisterBO terminalRegisterBO) {
        TerminalMQTT terminalMQTT = TerminalConverter.INSTANCE.terminalRegisterBO2TerminalMQTT(terminalRegisterBO);
        if (checkTerminalExists(terminalMQTT.getTopic())) {
            throw new BizException(BizExceptionType.TERMINAL_EXISTS);
        }
        terminalMQTTMapper.insertSelective(terminalMQTT);
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
            // 注册用户对action的权限等级;
            UserTerminalActionPermission permission = new UserTerminalActionPermission();
            permission.setUid(uid);
            permission.setTmId(tmId);
            // TODO test
            permission.setTaId(action.getId());
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
                .andTopicEqualTo(topic).andIsDeletedEqualTo(false);

        List<TerminalMQTT> terminalMQTTS = terminalMQTTMapper.selectByExample(example);
        if (terminalMQTTS.isEmpty()) {
            return null;
        }
        TerminalMQTT terminal = terminalMQTTS.get(0);
        TerminalBO terminalBO = new TerminalBO(terminalMQTTS.get(0));

        UserTerminalActionPermissionExample permissionExample = new UserTerminalActionPermissionExample();
        permissionExample.createCriteria()
                .andUidEqualTo(uid)
                .andTmIdEqualTo(terminal.getId())
                .andPermissionLevelLessThanOrEqualTo(TerminalActionPermissionLevelEnum.VISIBLE.getId());
        List<UserTerminalActionPermission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<TerminalAction> actions = permissionList.stream()
                .map(rec -> terminalActionMapper.selectByPrimaryKey(rec.getTaId())).collect(Collectors.toList());
        List<TerminalAction> aliveActions = actions.stream()
                .filter(rec -> rec.getIsDeleted().equals(false)).collect(Collectors.toList());

        terminalBO.addAction(TerminalConverter.INSTANCE.terminalAction2TerminalActionBO(aliveActions));
        return terminalBO;
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
