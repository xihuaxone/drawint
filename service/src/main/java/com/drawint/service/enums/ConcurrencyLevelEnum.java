package com.drawint.service.enums;

public enum ConcurrencyLevelEnum {
    MULTI_DEVICE_CONCURRENCY("20"),
    SINGLE_DEVICE_CONCURRENCY("10"),
    SERIAL("1"),
    ;
    private final String code;

    ConcurrencyLevelEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
