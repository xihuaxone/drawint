package com.drawint.service.model.termialaction;

public abstract class AbstractTerminalAction implements TerminalAction {
    private final String topic;

    public AbstractTerminalAction(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
