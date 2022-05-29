package com.drawint.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResult <T> {
    @Builder.Default
    private Integer code = 200;

    @Builder.Default
    private boolean success = true;

    private String errMsg;

    private T data;
}
