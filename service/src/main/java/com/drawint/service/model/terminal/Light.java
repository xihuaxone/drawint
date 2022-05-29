package com.drawint.service.model.terminal;

public interface Light extends TerminalBase {

    void open();

    void close();

    void switchStatus();

    void editLuminance(float luminanceRate);
}
