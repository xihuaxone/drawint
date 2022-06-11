package com.drawint.domain.enums;

public enum LightActionEnum {
    CONN_CHECK("CONN_CHECK", "检查终端是否连通", TerminalActionPermissionLevelEnum.OPERABLE),
    OPEN("OPEN", "", TerminalActionPermissionLevelEnum.EDITABLE),
    CLOSE("CLOSE", "", TerminalActionPermissionLevelEnum.EDITABLE),
    SWITCH("SWITCH", "", TerminalActionPermissionLevelEnum.EDITABLE),
    EDIT_LUMINANCE("EDIT_LUMINANCE", "", TerminalActionPermissionLevelEnum.OPERABLE),
    ;

    private final String code;

    private final TerminalActionPermissionLevelEnum permissionLevel;

    LightActionEnum(String code, String desc, TerminalActionPermissionLevelEnum permissionLevel) {
        this.code = code;
        this.permissionLevel = permissionLevel;
    }

    public String getCode() {
        return code;
    }

    public TerminalActionPermissionLevelEnum getPermissionLevel() {
        return permissionLevel;
    }

    public boolean isAllowed(Integer permissionLevel) {
        return permissionLevel <= this.getPermissionLevel().getId();
    }
}
