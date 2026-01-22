package com.my.answers.resolver;

import com.my.answers.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionServiceResolver {

    private final Map<Integer, QuestionService> byNumeroQuestion;

    public QuestionServiceResolver(List<QuestionService> services) {
        this.byNumeroQuestion = services.stream()
                .collect(Collectors.toMap(
                        QuestionService::questionNumber,
                        s -> s
                ));
    }

    public QuestionService resolve(int questionNumber) {
        QuestionService service = byNumeroQuestion.get(questionNumber);
        if (service == null) throw new IllegalArgumentException("This question doesn't exist: " + questionNumber);
        return service;
    }
}
