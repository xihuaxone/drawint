package com.drawint.service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RelayDeviceActLockAspect {

    @Pointcut("@annotation(com.drawint.service.annotation.RelayActionLock)")
    public void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        // TODO do lock
        log.info("locked");
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        // TODO release lock
        log.info("lock released");
    }
}
