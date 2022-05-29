package com.drawint.domain.converter;

import com.drawint.domain.bo.ActionRegisterBO;
import com.drawint.domain.bo.TerminalActionBO;
import com.drawint.domain.bo.TerminalRegisterBO;
import com.drawint.domain.entity.TerminalAction;
import com.drawint.domain.entity.TerminalMQTT;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-29T20:50:55+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
public class TerminalConverterImpl implements TerminalConverter {

    @Override
    public TerminalMQTT terminalRegisterBO2TerminalMQTT(TerminalRegisterBO terminalRegisterBO) {
        if ( terminalRegisterBO == null ) {
            return null;
        }

        TerminalMQTT terminalMQTT = new TerminalMQTT();

        terminalMQTT.setTopic( terminalRegisterBO.getTopic() );
        terminalMQTT.setName( terminalRegisterBO.getName() );

        return terminalMQTT;
    }

    @Override
    public List<TerminalAction> actionRegisterBO2TerminalAction(List<ActionRegisterBO> actionRegisterBOList) {
        if ( actionRegisterBOList == null ) {
            return null;
        }

        List<TerminalAction> list = new ArrayList<TerminalAction>( actionRegisterBOList.size() );
        for ( ActionRegisterBO actionRegisterBO : actionRegisterBOList ) {
            list.add( actionRegisterBO2TerminalAction( actionRegisterBO ) );
        }

        return list;
    }

    @Override
    public TerminalAction actionRegisterBO2TerminalAction(ActionRegisterBO actionRegisterBO) {
        if ( actionRegisterBO == null ) {
            return null;
        }

        TerminalAction terminalAction = new TerminalAction();

        terminalAction.setName( actionRegisterBO.getName() );
        terminalAction.setCode( actionRegisterBO.getCode() );
        if ( actionRegisterBO.getConcurrencyLevel() != null ) {
            terminalAction.setConcurrencyLevel( actionRegisterBO.getConcurrencyLevel().intValue() );
        }
        terminalAction.setInterval( actionRegisterBO.getInterval() );

        return terminalAction;
    }

    @Override
    public TerminalActionBO terminalAction2TerminalActionBO(TerminalAction terminalAction) {
        if ( terminalAction == null ) {
            return null;
        }

        TerminalActionBO terminalActionBO = new TerminalActionBO();

        terminalActionBO.setId( terminalAction.getId() );
        terminalActionBO.setTmId( terminalAction.getTmId() );
        terminalActionBO.setName( terminalAction.getName() );
        terminalActionBO.setCode( terminalAction.getCode() );
        if ( terminalAction.getConcurrencyLevel() != null ) {
            terminalActionBO.setConcurrencyLevel( terminalAction.getConcurrencyLevel().byteValue() );
        }
        terminalActionBO.setInterval( terminalAction.getInterval() );

        return terminalActionBO;
    }

    @Override
    public List<TerminalActionBO> terminalAction2TerminalActionBO(List<TerminalAction> terminalActionList) {
        if ( terminalActionList == null ) {
            return null;
        }

        List<TerminalActionBO> list = new ArrayList<TerminalActionBO>( terminalActionList.size() );
        for ( TerminalAction terminalAction : terminalActionList ) {
            list.add( terminalAction2TerminalActionBO( terminalAction ) );
        }

        return list;
    }
}
