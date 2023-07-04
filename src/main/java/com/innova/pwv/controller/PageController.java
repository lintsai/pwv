package com.innova.pwv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * index page
 */
@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
