package com.my.answers.entity.dto;

public record AnswerRequest(String dataType, String question,
                            String snippet, Integer questionNumber,
                            String language) {
}
