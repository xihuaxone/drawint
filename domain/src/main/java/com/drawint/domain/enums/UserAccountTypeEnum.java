package com.drawint.domain.enums;

public enum UserAccountTypeEnum {
    DRAW_INT("DRAW_INT");
    ;
    private final String code;

    UserAccountTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
