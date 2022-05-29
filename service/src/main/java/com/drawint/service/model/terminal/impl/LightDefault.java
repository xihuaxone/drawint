package com.drawint.service.model.terminal.impl;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.service.enums.ConcurrencyLevelEnum;
import com.drawint.service.enums.LightActionEnum;
import com.drawint.service.enums.PinESP01SEnum;
import com.drawint.service.model.termialaction.TerminalAction;
import com.drawint.service.model.termialaction.impl.TerminalActionESP01S;
import com.drawint.service.model.terminal.Light;

import java.util.List;

public class LightDefault implements Light {
    private final String topic;
    private final List<String> actionList;
    private final ConcurrencyLevelEnum concurrencyLevel;
    private final int interval;
    private final TerminalAction terminalAction;
    private final PinESP01SEnum controlPin;

    public String getTopic() {
        return topic;
    }

    public List<String> getActionList() {
        return actionList;
    }

    public ConcurrencyLevelEnum getConcurrencyLevel() {
        return concurrencyLevel;
    }

    public int getInterval() {
        return interval;
    }

    public TerminalAction getTerminalAction() {
        return terminalAction;
    }

    public PinESP01SEnum getControlPin() {
        return controlPin;
    }

    private void checkAction(LightActionEnum lightAction) {
        if (!actionList.contains(lightAction.getCode())) {
            throw new BizException(BizExceptionType.TERMINAL_ACTION_ILLEGAL,
                    "apply action illegal: " + lightAction.getCode());
        }
    }

    public LightDefault(String topic, List<String> actionList, ConcurrencyLevelEnum concurrencyLevel, int interval) {
        this.topic = topic;
        this.actionList = actionList;
        this.concurrencyLevel = concurrencyLevel;
        this.interval = interval;
        this.terminalAction = new TerminalActionESP01S(topic);
        this.controlPin = PinESP01SEnum.PIN_0;
    }

    @Override
    public void open() {
        checkAction(LightActionEnum.OPEN);
        terminalAction.enable(this.controlPin);
    }

    @Override
    public void close() {
        checkAction(LightActionEnum.CLOSE);
        terminalAction.disable(this.controlPin);
    }

    @Override
    public void switchStatus() {
        checkAction(LightActionEnum.SWITCH);
        terminalAction.switchStatus(this.controlPin);
    }

    @Override
    public void editLuminance(float luminanceRatio) {
        checkAction(LightActionEnum.EDIT_LUMINANCE);
        terminalAction.setPWMEnableRatio(this.controlPin, luminanceRatio);
    }
}
