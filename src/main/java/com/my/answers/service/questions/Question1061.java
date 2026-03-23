package com.my.answers.service.questions;

import com.my.answers.entity.Language;
import com.my.answers.service.MessageProvider;
import com.my.answers.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1061 implements QuestionService {

    private final MessageProvider messages;

    @Override
    public int questionNumber() {
        return 1061;
    }

    @Override
    public String response(List<String> data, Language language) {
        try {
            StringBuilder sb = new StringBuilder();
            int startDay =  Integer.parseInt(data.get(0).substring(4).trim());
            int endDay =  Integer.parseInt(data.get(2).substring(4).trim());

            List<Integer> startTime = Arrays.stream(data.get(1).split(" : ")).map(Integer::parseInt).toList();
            List<Integer> endTime = Arrays.stream(data.get(3).split(" : ")).map(Integer::parseInt).toList();

            CharSequence timeStartFormat = "P"+startDay+"DT"+startTime.get(0)+"H"+startTime.get(1)+"M"+startTime.get(2)+"S";
            CharSequence timeEndFormat = "P"+endDay+"DT"+endTime.get(0)+"H"+endTime.get(1)+"M"+endTime.get(2)+"S";

            Duration startDuration = Duration.parse(timeStartFormat);
            Duration endDuration = Duration.parse(timeEndFormat);
            Duration finalDuration = endDuration.minus(startDuration);

            sb.append(finalDuration.toDays()).append(" dia(s)").append("\n");
            sb.append(finalDuration.toHoursPart()).append(" hora(s)").append("\n");
            sb.append(finalDuration.toMinutesPart()).append(" minuto(s)").append("\n");
            sb.append(finalDuration.toSecondsPart()).append(" segundo(s)");
            return sb.toString();
        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return messages.get("error.parse.input", language);
        }
    }
}