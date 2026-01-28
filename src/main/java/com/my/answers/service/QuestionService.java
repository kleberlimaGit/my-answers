package com.my.answers.service;

import com.my.answers.entity.Language;

public interface QuestionService {
    int questionNumber();
    String response(String data, Language language);
}
