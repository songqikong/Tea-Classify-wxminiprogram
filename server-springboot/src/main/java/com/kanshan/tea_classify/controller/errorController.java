package com.kanshan.tea_classify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class errorController {

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
