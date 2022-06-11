package com.drawint.domain.enums;

public enum TerminalActionCodeEnum {
    CONN_CHECK("CONN_CHECK", "检查终端是否连通", TerminalActionPermissionLevelEnum.OPERABLE),
    ;

    private final String code;

    private final TerminalActionPermissionLevelEnum permissionLevel;

    TerminalActionCodeEnum(String code, String desc, TerminalActionPermissionLevelEnum permissionLevel) {
        this.code = code;
        this.permissionLevel = permissionLevel;
    }

    public String getCode() {
        return code;
    }

    private TerminalActionPermissionLevelEnum getPermissionLevel() {
        return permissionLevel;
    }

    public boolean isAllowed(Integer permissionLevel) {
        return permissionLevel <= this.getPermissionLevel().getId();
    }
}
