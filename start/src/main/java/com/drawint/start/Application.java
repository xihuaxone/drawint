package com.drawint.start;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.SpringProperties;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.drawint.service", "com.drawint.start.controller", "com.drawint.start.advice", "com.drawint.start.aop", "com.drawint.common.utils", "com.drawint.dal.relay"})
@MapperScan(basePackages = {"com.drawint.dal.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        String address = SpringProperties.getProperty("server.address");
        log.info("started with ip {}, port {}", address, "port");
    }
}
