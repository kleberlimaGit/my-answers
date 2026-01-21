package com.my.answers.controller;

import com.my.answers.entity.dto.QuestionRequest;
import com.my.answers.service.QuestionServiceResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswersController {

    private final QuestionServiceResolver resolver;

    @PostMapping
    public String get(@RequestBody QuestionRequest data) {
        return resolver.resolve(data.questionNumber()).response(data.request());
    }
}
