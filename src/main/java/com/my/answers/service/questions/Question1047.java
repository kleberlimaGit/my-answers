package com.my.answers.service.questions;

import com.my.answers.entity.Language;
import com.my.answers.service.MessageProvider;
import com.my.answers.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1047 implements QuestionService {

    private final MessageProvider messages;

    @Override
    public int questionNumber() {
        return 1047;
    }

    @Override
    public String response(List<String> data, Language language) {
        try {
            StringBuilder sb = new StringBuilder();
            LocalTime startGame = LocalTime.of(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)));
            LocalTime endGame = LocalTime.of(Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)));
            Duration duration = Duration.between(startGame, endGame);
            int hours = 24;
            int minutes = 0;
            if(!(duration.toHoursPart() == 0 && duration.toMinutesPart() == 0)){
                if(duration.isNegative()){
                    duration = duration.plusDays(1);
                }
                hours = duration.toHoursPart();
                minutes = duration.toMinutesPart();
            }

            sb.append(String.format("O JOGO DUROU %d HORA(S) E %d MINUTO(S)", hours, minutes));
            return sb.toString();
        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return messages.get("error.parse.input", language);
        }
    }

}
