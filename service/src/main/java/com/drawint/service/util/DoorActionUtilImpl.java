package com.drawint.service.util;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.domain.bo.TerminalActionBO;
import com.drawint.domain.bo.TerminalBO;
import com.drawint.domain.enums.DoorActionEnum;
import com.drawint.domain.enums.PinESP01SEnum;
import com.drawint.service.model.termialaction.impl.TerminalActionESP01S;

public class DoorActionUtilImpl implements TerminalActionUtil {
    @Override
    public void doAction(TerminalBO terminalBO, String action) {
        TerminalActionESP01S terminal = new TerminalActionESP01S(terminalBO.getTopic());
        if (action.equals(DoorActionEnum.RESET.getCode())) {
            terminal.click(PinESP01SEnum.PIN_2);
        }
        else {
            throw new BizException(BizExceptionType.TERMINAL_ACTION_NOT_SUPPORTED_YET);
        }
    }

    @Override
    public boolean checkAction(TerminalBO terminalBO, String action) {
        if (!DoorActionEnum.contains(action)) {
            return false;
        }
        TerminalActionBO actionBO = terminalBO.getAction(action);
        if (actionBO == null || !DoorActionEnum.valueOf(action).isAllowed(actionBO.getPermission().getPermissionLevel())) {
            return false;
        }
        return true;
    }
}
