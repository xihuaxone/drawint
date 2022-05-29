package com.drawint.start.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Order(1)
@Slf4j
@Aspect
@Component
public class RequestAspect {
    @Pointcut("execution(public * com.drawint.start.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            throw new RuntimeException("request with no attributes");
        }
        HttpServletRequest request = attributes.getRequest();
        log.info("{} [{}] params: {}", request.getMethod(), request.getRequestURI(), joinPoint.getArgs());
    }
}
