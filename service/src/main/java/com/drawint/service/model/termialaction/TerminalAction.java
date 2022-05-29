package com.drawint.service.model.termialaction;

import com.drawint.service.enums.PinESP01SEnum;

public interface TerminalAction {
    void enable(PinESP01SEnum pin);

    void disable(PinESP01SEnum pin);

    void switchStatus(PinESP01SEnum pin);

    void click(PinESP01SEnum pin);

    void setPWMFrequency(PinESP01SEnum pin, int frequency);

    void setPWMEnableRatio(PinESP01SEnum pin, float ratio);
}
