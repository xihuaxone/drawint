package com.drawint.domain.enums;

public enum ConcurrencyLevelEnum {
    MULTI_DEVICE_CONCURRENCY(20),
    SINGLE_DEVICE_CONCURRENCY(10),
    SERIAL(1),
    ;
    private final Integer code;

    ConcurrencyLevelEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
