package com.drawint.domain.enums;

public enum TerminalTypeEnum {
    UNKNOWN("UNKNOWN"),
    DOOR_ESP01S("DOOR_ESP01S"),
    ;
    private final String code;

    TerminalTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static boolean contains(String typeCode) {
        for (TerminalTypeEnum typeEnum : TerminalTypeEnum.values()) {
            if (typeEnum.getCode().equals(typeCode)) {
                return true;
            }
        }
        return false;
    }
}
