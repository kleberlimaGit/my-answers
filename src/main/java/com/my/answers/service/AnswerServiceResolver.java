package com.my.answers.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnswerServiceResolver {

    private final Map<Integer, AnswerService> byNumeroQuestion;

    public AnswerServiceResolver(List<AnswerService> services) {
        this.byNumeroQuestion = services.stream()
                .collect(Collectors.toMap(
                        AnswerService::numeroQuestao,
                        s -> s
                ));
    }

    public AnswerService resolve(int questionNumber) {
        AnswerService service = byNumeroQuestion.get(questionNumber);
        if (service == null) throw new IllegalArgumentException("This question doesn't exist: " + questionNumber);
        return service;
    }
}
