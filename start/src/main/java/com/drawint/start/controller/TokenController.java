package com.drawint.start.controller;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.common.utils.JWTUtil;
import com.drawint.common.utils.SecretKeyUtil;
import com.drawint.domain.ApiResult;
import com.drawint.domain.bo.UserIdentifyBO;
import com.drawint.domain.dto.TokenPayload;
import com.drawint.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Validated
@RestController
@ResponseBody
@RequestMapping(path = "/tokens")
public class TokenController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ApiResult<Void> login(HttpServletResponse response, @RequestBody @Valid UserIdentifyBO identifyBO) {
        if (!userService.identify(identifyBO)) {
            throw new BizException(BizExceptionType.LOGIN_USER_OR_PASSWORD_ERROR);
        }
        TokenPayload tokenPayload = new TokenPayload();
        tokenPayload.setUid(userService.getUidByAccount(identifyBO.getAccount()));
        tokenPayload.setLoginAccount(identifyBO.getAccount());
        String tokenStr = JWTUtil.sign(tokenPayload.toPayloadMap());
        response.setHeader(AUTHORIZATION, tokenStr);
        return ApiResult.<Void>builder().build();
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ApiResult<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(AUTHORIZATION, "");
        return ApiResult.<Void>builder().build();
    }

    @RequestMapping(path = "/rsa_public_key", method = RequestMethod.GET)
    public ApiResult<String> getRsaPublicKey() {
        return ApiResult.<String>builder().data(
                Base64.encodeBase64String(SecretKeyUtil.getPublicKey().getEncoded())).build();
    }
}
