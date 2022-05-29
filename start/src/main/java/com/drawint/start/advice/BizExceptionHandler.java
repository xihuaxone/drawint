package com.drawint.start.advice;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.domain.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ResponseBody
@ControllerAdvice
public class BizExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ApiResult<Void> handler(Exception e) {
        log.error("uncaught error: ", e);
        Integer code = e instanceof BizException ? ((BizException) e).getCode() : 600;
        return ApiResult.<Void>builder().success(false).code(code).errMsg(e.toString()).build();
    }

//    @ExceptionHandler(value = ConstraintViolationException.class)
//    public ApiResult<Void> requestValidateHandler(Exception e) {
//        BizException bizException = new BizException(BizExceptionType.METHOD_PARAM_VALIDATE_FAIL, e.getMessage());
//
//        return ApiResult.<Void>builder().success(false).code(bizException.getCode())
//                .errMsg(bizException.toString()).build();
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult<Void> requestValidateHandler(MethodArgumentNotValidException e) {
        List<String> errMsg = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> errMsg.add(err.getDefaultMessage()));
        BizException bizException = new BizException(
                BizExceptionType.BEAN_PARAM_VALIDATE_FAIL, String.join("; ", errMsg));

        return ApiResult.<Void>builder().success(false).code(bizException.getCode())
                .errMsg(bizException.toString()).build();
    }

//    @ExceptionHandler(value = BindException.class)
//    public ApiResult<Void> requestValidateHandler(BindException e) {
//        BizException bizException = new BizException(BizExceptionType.PARAM_BIND_FAIL, e.getMessage());
//
//        return ApiResult.<Void>builder().success(false).code(bizException.getCode())
//                .errMsg(bizException.toString()).build();
//    }
}
