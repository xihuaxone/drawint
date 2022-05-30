package com.drawint.domain.bo;

import lombok.Data;

@Data
public class TerminalActionBO {
    private Long id;

    private Long tmId;

    private String name;

    private String code;

    private Integer concurrencyLevel;

    private Integer interval;
}
