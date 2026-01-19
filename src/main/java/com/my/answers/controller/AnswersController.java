package com.my.answers.controller;

import com.my.answers.entity.dto.AnswerRequest;
import com.my.answers.service.AnswerServiceResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswersController {

    private final AnswerServiceResolver resolver;

    @PostMapping
    public String get(@RequestBody AnswerRequest data) {
        return resolver.resolve(data.getQuestionNumber()).response(data.getRequest());
    }
}
