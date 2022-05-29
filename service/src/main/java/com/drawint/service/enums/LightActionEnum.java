package com.drawint.service.enums;

public enum LightActionEnum {
    OPEN("OPEN"),
    CLOSE("CLOSE"),
    SWITCH("SWITCH"),
    EDIT_LUMINANCE("EDIT_LUMINANCE"),
    ;
    private final String code;

    public String getCode() {
        return code;
    }

    LightActionEnum(String code) {
        this.code = code;
    }
}
