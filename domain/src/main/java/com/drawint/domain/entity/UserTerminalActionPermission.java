package com.drawint.domain.entity;

public class UserTerminalActionPermission {
    private Long id;

    private Long uid;

    private Long tmId;

    private Long taId;

    private Integer permissionLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getTmId() {
        return tmId;
    }

    public void setTmId(Long tmId) {
        this.tmId = tmId;
    }

    public Long getTaId() {
        return taId;
    }

    public void setTaId(Long taId) {
        this.taId = taId;
    }

    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}