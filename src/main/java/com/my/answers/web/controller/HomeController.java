package com.my.answers.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({"", "/"})
    public String root() {
        return "redirect:/pt/answers";
    }

    @GetMapping({"/{lang:pt|en}", "/{lang:pt|en}/"})
    public String langRoot(@PathVariable String lang) {
        return "redirect:/" + lang + "/answers";
    }
}
