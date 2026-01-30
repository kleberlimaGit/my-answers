package com.my.answers.service.questions;

import com.my.answers.entity.Language;
import com.my.answers.service.MessageProvider;
import com.my.answers.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1042 implements QuestionService {

    private final MessageProvider messages;

    @Override
    public int questionNumber() {
        return 1042;
    }

    @Override
    public String response(List<String> data, Language language) {
        try {
            StringBuilder sb = new StringBuilder();

            List<Integer> numbers = Arrays.asList(
                    Integer.parseInt(data.get(0).trim()),
                    Integer.parseInt(data.get(1).trim()),
                    Integer.parseInt(data.get(2).trim())
            );
            numbers.sort(Integer::compareTo);
            numbers.forEach(number -> sb.append(number).append("\n"));
            sb.append("\n");
            data.forEach(number -> sb.append(number).append("\n"));
            return sb.toString();
        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return messages.get("error.parse.input", language);
        }
    }

}
