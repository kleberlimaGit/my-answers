package com.my.answers.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record QuestionRequest(
        @NotBlank(message = "You must to send some data")
        @Size(max = 100, message = "Max size input is 100")
        List<String> request,

        int questionNumber
) {
}
