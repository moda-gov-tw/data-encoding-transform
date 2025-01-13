package com.fullstack.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntryController {
    @RequestMapping("/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}
