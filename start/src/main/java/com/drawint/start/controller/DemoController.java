package com.drawint.start.controller;

import com.drawint.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;

@Validated
@RestController
@RequestMapping(path = "/demo")
public class DemoController {
    @Autowired
    DemoService demoService;

    @RequestMapping(path = "")
    public String index(@RequestParam @Null String uid) {
        System.out.println(uid);
        return demoService.hello();
    }
}
