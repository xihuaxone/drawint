package com.drawint.service.model.termialaction.impl;

import com.drawint.common.utils.MQTTUtil;
import com.drawint.service.enums.PinESP01SEnum;
import com.drawint.service.model.termialaction.AbstractTerminalAction;

public class TerminalActionESP01S extends AbstractTerminalAction {
    private static final String ACTION_DISABLE = "1";
    private static final String ACTION_ENABLE = "2";
    private static final String ACTION_SWITCH_STATUS = "3";
    private static final String ACTION_CLICK = "4";

    public TerminalActionESP01S(String topic) {
        super(topic);
    }

    @Override
    public void enable(PinESP01SEnum pin) {
        MQTTUtil.send(getTopic(), pin.getCode() + ACTION_ENABLE);
    }

    @Override
    public void disable(PinESP01SEnum pin) {
        MQTTUtil.send(getTopic(), pin.getCode() + ACTION_DISABLE);
    }

    @Override
    public void switchStatus(PinESP01SEnum pin) {
        MQTTUtil.send(getTopic(), pin.getCode() + ACTION_SWITCH_STATUS);
    }

    @Override
    public void click(PinESP01SEnum pin) {
        MQTTUtil.send(getTopic(), pin.getCode() + ACTION_CLICK);
    }

    @Override
    public void setPWMFrequency(PinESP01SEnum pin, int frequency) {
        // TODO;
    }

    @Override
    public void setPWMEnableRatio(PinESP01SEnum pin, float ratio) {
        // TODO;
    }
}
