package com.drawint.domain.enums;

public enum TerminalActionPermissionLevelEnum {
    VISIBLE(99, "可见"),
    OPERABLE(60, "可操作"),
    EDITABLE(30, "可编辑"),
    ADMIN(1, "管理员"),
    ;
    private final Integer id;

    TerminalActionPermissionLevelEnum(Integer id, String code) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static boolean isVisible(Integer permissionLevelId) {
        return permissionLevelId <= TerminalActionPermissionLevelEnum.VISIBLE.getId();
    }

    public static boolean isVisible(TerminalActionPermissionLevelEnum permissionLevel) {
        return permissionLevel.getId() <= TerminalActionPermissionLevelEnum.VISIBLE.getId();
    }
}
