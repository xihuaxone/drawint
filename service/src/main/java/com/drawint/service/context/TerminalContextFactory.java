package com.drawint.service.context;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.domain.bo.TerminalBO;
import com.drawint.domain.enums.TerminalTypeEnum;
import com.drawint.service.util.DoorActionUtilImpl;

public class TerminalContextFactory {
    public static TerminalContext getInstance(TerminalBO terminal) {

        if (terminal.getType().equals(TerminalTypeEnum.DOOR_ESP01S.getCode())) {
            return new TerminalContext(new DoorActionUtilImpl());
        }
        else {
            throw new BizException(BizExceptionType.TERMINAL_TYPE_NOT_EXISTS);
        }
    }
}
