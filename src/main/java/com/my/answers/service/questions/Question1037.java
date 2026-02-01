package com.my.answers.service.questions;

import com.my.answers.entity.Language;
import com.my.answers.service.MessageProvider;
import com.my.answers.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1037 implements QuestionService {

    private final MessageProvider messages;

    @Override
    public int questionNumber() {
        return 1037;
    }

    @Override
    public String response(List<String> data, Language language) {
        try {
            StringBuilder sb = new StringBuilder();
           String[] intervals = {"0,25","25,50","50,75","75,100"};
           double value = Double.parseDouble(data.get(0).trim());
            for (String interval : intervals) {
                String[] limits = interval.split(",");
                double lowerBound = Double.parseDouble(limits[0]) != 0 ? Double.parseDouble(limits[0]) + 0.0001 : Double.parseDouble(limits[0]);
                double upperBound = Double.parseDouble(limits[1]);
                if (value >= lowerBound && value <= upperBound) {
                    String bracketOrParentheses = lowerBound % 5 == 0 ? "[" : "(";
                    sb.append(String.format("Intervalo %s%s]", bracketOrParentheses, interval));
                    return sb.toString();
                }
            }
            return sb.append("Fora de intervalo\n").toString();

        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return messages.get("error.parse.input", language);
        }
    }

}
