package com.innova.pwv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * index page
 */
@Controller
public class PageController {
    /**
     * home page (password validation)
     * @return index.html
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
