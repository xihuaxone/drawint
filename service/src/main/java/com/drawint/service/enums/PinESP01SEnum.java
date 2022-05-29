package com.drawint.service.enums;

public enum PinESP01SEnum {
    PIN_0("0"),
    PIN_2("2"),
    ;
    private final String code;

    PinESP01SEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
