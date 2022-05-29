package com.drawint.domain.converter;

import com.drawint.domain.bo.ActionRegisterBO;
import com.drawint.domain.bo.TerminalActionBO;
import com.drawint.domain.bo.TerminalRegisterBO;
import com.drawint.domain.entity.TerminalAction;
import com.drawint.domain.entity.TerminalMQTT;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TerminalConverter {
    TerminalConverter INSTANCE = Mappers.getMapper(TerminalConverter.class);

    TerminalMQTT terminalRegisterBO2TerminalMQTT(TerminalRegisterBO terminalRegisterBO);

    List<TerminalAction> actionRegisterBO2TerminalAction(List<ActionRegisterBO> actionRegisterBOList);

    TerminalAction actionRegisterBO2TerminalAction(ActionRegisterBO actionRegisterBO);

    TerminalActionBO terminalAction2TerminalActionBO(TerminalAction terminalAction);
    List<TerminalActionBO> terminalAction2TerminalActionBO(List<TerminalAction> terminalActionList);
}
