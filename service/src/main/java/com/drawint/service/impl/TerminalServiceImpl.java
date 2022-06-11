package com.drawint.service.impl;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.domain.bo.TerminalBO;
import com.drawint.service.TerminalMngService;
import com.drawint.service.TerminalService;
import com.drawint.service.context.TerminalContext;
import com.drawint.service.context.TerminalContextFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TerminalServiceImpl implements TerminalService {
    @Autowired
    TerminalMngService terminalMngService;

    @Override
    public void doAction(Long tmId, String actionCode, Long uid) {
        // 验证用户是否有操作权限;
        TerminalBO terminalBO = terminalMngService.get(tmId, uid);
        if (terminalBO == null) {
            throw new BizException(BizExceptionType.TERMINAL_NOT_EXISTS, "tmId = " + tmId);
        }
        TerminalContext terminalContext = TerminalContextFactory.getInstance(terminalBO);
        terminalContext.doAction(terminalBO, actionCode);

        log.info("do action " + tmId + " action " + actionCode + " uid " + uid);
    }
}
