package com.my.answers.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{lang}/about-me")
public class AboutMeController {

    @GetMapping
    public String aboutMe(@PathVariable String lang) {
        if(lang.equals("pt")){
            return "about-me/about-me-pt_BR";
        }
        return "about-me/about-me-en_US";
    }
}
