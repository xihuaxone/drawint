package com.drawint.service.model.termialaction;

import com.drawint.service.enums.PinESP01SEnum;

public abstract class AbstractTerminalAction implements TerminalAction {
    private final String topic;

    public AbstractTerminalAction(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
