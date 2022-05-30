package com.drawint.domain.enums;

public enum TerminalActionCodeEnum {
    CONN_CHECK("CONN_CHECK", "检查终端是否连通"),
    ;

    private final String code;

    TerminalActionCodeEnum(String code, String desc) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
