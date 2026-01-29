package com.my.answers.service;

import com.my.answers.entity.Language;

import java.util.List;

public interface QuestionService {
    int questionNumber();
    String response(List<String> data, Language language);
}
