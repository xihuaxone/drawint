package com.drawint.common.exception;


import com.drawint.common.BizExceptionType;

public class BizException extends RuntimeException {
    private final Integer code;

    public BizException(BizExceptionType bizExceptionType) {
        super(bizExceptionType.getType());
        this.code = bizExceptionType.getCode();
    }

    public BizException(BizExceptionType bizExceptionType, String errMsg) {
        super(bizExceptionType.getType() + ": " + errMsg);
        this.code = bizExceptionType.getCode();
    }

    public BizException(Exception e) {
        super(e.toString());
        this.code = 599;
    }

    public Integer getCode() {
        return code;
    }

    public boolean isTypeOf(BizExceptionType bizExceptionType) {
        return this.code.equals(bizExceptionType.getCode());
    }

    @Override
    public String toString() {
        String message = getLocalizedMessage();
        return (message != null) ? ("BizException: " + message) : "BizException";
    }
}
