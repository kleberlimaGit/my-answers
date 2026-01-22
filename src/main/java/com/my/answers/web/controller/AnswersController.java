package com.my.answers.web.controller;

import com.my.answers.entity.dto.QuestionRequest;
import com.my.answers.resolver.QuestionServiceResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@RequestMapping("/{lang}/answers")
@RequiredArgsConstructor
public class AnswersController {

    private final QuestionServiceResolver resolver;
    private final LocaleResolver localeResolver;

    @PostMapping
    public String get(@RequestBody QuestionRequest data) {
        return resolver.resolve(data.questionNumber()).response(data.request());
    }

    @GetMapping("/change-language")
    @ResponseBody
    public void changeLanguage(@RequestParam String lang,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        Locale locale = Locale.forLanguageTag(lang);
        localeResolver.setLocale(request, response, locale);
    }
}
