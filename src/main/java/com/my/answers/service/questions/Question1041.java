package com.my.answers.service.questions;

import com.my.answers.entity.Language;
import com.my.answers.service.MessageProvider;
import com.my.answers.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1041 implements QuestionService {

    private final MessageProvider messages;

    @Override
    public int questionNumber() {
        return 1041;
    }

    @Override
    public String response(List<String> data, Language language) {
        try {
            StringBuilder sb = new StringBuilder();
            double x = Double.parseDouble(data.get(0).trim());
            double y = Double.parseDouble(data.get(1).trim());
            String quadrant = String.format("%d %d", (int) Math.signum(x), (int) Math.signum(y));
            Map<String, String> quadrantMap = Map.of(
                    "1 1","Q1",
                    "-1 1","Q2",
                     "-1 -1","Q3",
                    "1 -1","Q4",
                    "1 0","Eixo X",
                    "-1 0","Eixo X",
                    "0 1","Eixo Y",
                    "0 -1","Eixo Y",
                    "0 0","Origem"
            );
            return sb.append(quadrantMap.get(quadrant)).toString();

        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return messages.get("error.parse.input", language);
        }
    }

}
