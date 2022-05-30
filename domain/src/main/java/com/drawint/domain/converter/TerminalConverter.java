package com.drawint.domain.converter;

import com.drawint.domain.bo.ActionRegisterBO;
import com.drawint.domain.bo.TerminalActionBO;
import com.drawint.domain.bo.TerminalRegisterBO;
import com.drawint.domain.entity.TerminalAction;
import com.drawint.domain.entity.TerminalMQTT;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TerminalConverter {
    TerminalConverter INSTANCE = Mappers.getMapper(TerminalConverter.class);

    TerminalMQTT terminalRegisterBO2TerminalMQTT(TerminalRegisterBO terminalRegisterBO);

    @Mapping(source = "actionRegisterBOList.interval", target = "aroundInterval")
    List<TerminalAction> actionRegisterBO2TerminalAction(List<ActionRegisterBO> actionRegisterBOList);

    @Mapping(source = "actionRegisterBO.interval", target = "aroundInterval")
    TerminalAction actionRegisterBO2TerminalAction(ActionRegisterBO actionRegisterBO);

    @Mapping(source = "terminalAction.aroundInterval", target = "interval")
    TerminalActionBO terminalAction2TerminalActionBO(TerminalAction terminalAction);

    @Mapping(source = "terminalActionList.aroundInterval", target = "interval")
    List<TerminalActionBO> terminalAction2TerminalActionBO(List<TerminalAction> terminalActionList);
}
