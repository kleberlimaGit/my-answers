package com.my.answers.service.questions;

import com.my.answers.entity.Language;
import com.my.answers.service.MessageProvider;
import com.my.answers.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1019 implements QuestionService {

    private final MessageProvider messages;

    @Override
    public int questionNumber() {
        return 1019;
    }

    @Override
    public String response(List<String> data, Language language) {
        try {
            long seconds = Long.parseLong(data.get(0).trim());
            Duration total = Duration.ofSeconds(seconds);
            int horas = (int) total.toHours();
            int minutos = total.toMinutesPart();
            int seg = total.toSecondsPart();

            return horas+":"+minutos+":"+seg;
        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return messages.get("error.parse.input", language);
        }
    }
}
