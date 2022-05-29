package com.drawint.domain.bo;

import lombok.Data;

@Data
public class ActionRegisterBO {

    private String name;

    private String code;

    private Short concurrencyLevel;

    private Integer interval;

    private Integer userPermissionLevel;
}
