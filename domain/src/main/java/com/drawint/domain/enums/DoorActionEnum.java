package com.drawint.domain.enums;

public enum DoorActionEnum {
    CONN_CHECK("CONN_CHECK", "检查终端是否连通", TerminalActionPermissionLevelEnum.OPERABLE),
    OPEN("OPEN", "", TerminalActionPermissionLevelEnum.OPERABLE),
    CLOSE("CLOSE", "", TerminalActionPermissionLevelEnum.OPERABLE),
    RESET("RESET", "", TerminalActionPermissionLevelEnum.OPERABLE),
    ;

    private final String code;

    private final TerminalActionPermissionLevelEnum permissionLevel;

    DoorActionEnum(String code, String desc, TerminalActionPermissionLevelEnum permissionLevel) {
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

    public static boolean contains(String actionCode) {
        for (DoorActionEnum value : DoorActionEnum.values()) {
            if (value.getCode().equals(actionCode)) {
                return true;
            }
        }
        return false;
    }
}
