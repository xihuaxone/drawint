package com.drawint.service.impl;

import com.drawint.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String hello() {
        return "hello i'm ok";
    }
}
