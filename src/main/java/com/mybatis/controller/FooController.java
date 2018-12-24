package com.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FooController {

    @RequestMapping("go")
    public String goTo(){
        int a=2/0;
        return "index";
    }
}
