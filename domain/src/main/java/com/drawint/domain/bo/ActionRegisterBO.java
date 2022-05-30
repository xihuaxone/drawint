package com.drawint.domain.bo;

import lombok.Data;

@Data
public class ActionRegisterBO {

    private String name;

    private String code;

    private Integer concurrencyLevel;

    private Integer interval;

    private Integer userPermissionLevel;
}
