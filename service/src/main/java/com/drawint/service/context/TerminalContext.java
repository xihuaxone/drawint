package com.drawint.service.context;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.domain.bo.TerminalBO;
import com.drawint.service.util.TerminalActionUtil;

public class TerminalContext {
    private final TerminalActionUtil actionUtil;

    public TerminalContext(TerminalActionUtil actionUtil) {
        this.actionUtil = actionUtil;
    }

    public void doAction(TerminalBO terminalBO, String actionCode) {
        if (!actionUtil.checkAction(terminalBO, actionCode)) {
            throw new BizException(BizExceptionType.TERMINAL_ACTION_ILLEGAL);
        }
        actionUtil.doAction(terminalBO, actionCode);
    }
}
