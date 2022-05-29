package com.drawint.domain.enums;

public enum DoorActionEnum {
    OPEN("OPEN"),
    CLOSE("CLOSE"),
    RESET("RESET"),
    ;

    private final String code;

    DoorActionEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
