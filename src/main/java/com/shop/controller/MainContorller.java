package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContorller {

    @GetMapping(value="/")
    public String main() {
        return "main";
    }
}
