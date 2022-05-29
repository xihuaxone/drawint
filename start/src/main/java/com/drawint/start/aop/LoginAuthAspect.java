package com.drawint.start.aop;

import com.alibaba.fastjson.JSON;
import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.common.utils.JWTUtil;
import com.drawint.common.utils.StringUtil;
import com.drawint.domain.dto.TokenPayload;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Order(2)
@Slf4j
@Aspect
@Component
public class LoginAuthAspect {
    private final BizException loginAuthError = new BizException(
            BizExceptionType.LOGIN_AUTH_ERROR, "loginAuth cookie is null");

    @Pointcut("@annotation(com.drawint.start.annotation.LoginAuth)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            throw new RuntimeException("request with no attributes");
        }
        String token = attributes.getRequest().getHeader(AUTHORIZATION);
        if (StringUtil.isBlank(token)) {
            throw new BizException(BizExceptionType.NOT_LOGGED_IN_ERROR);
        }
        Claims claims = JWTUtil.unSign(token);
        TokenPayload payload = new TokenPayload(claims);
        attributes.getRequest().setAttribute("payload", payload);
    }
}
