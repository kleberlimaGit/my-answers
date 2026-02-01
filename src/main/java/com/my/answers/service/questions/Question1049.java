package com.my.answers.service.questions;

import com.my.answers.entity.Language;
import com.my.answers.service.MessageProvider;
import com.my.answers.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1049 implements QuestionService {

    private final MessageProvider messages;

    @Override
    public int questionNumber() {
        return 1049;
    }

    @Override
    public String response(List<String> data, Language language) {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> type = Arrays.asList(data.get(0).trim(), data.get(1).trim(), data.get(2).trim());
            Map<List<String>, String> animals = Map.of(
                    Arrays.asList("vertebrado", "ave", "carnivoro"), "aguia",
                    Arrays.asList("vertebrado", "ave", "onivoro"), "pomba",
                    Arrays.asList("vertebrado", "mamifero", "onivoro"), "homem",
                    Arrays.asList("vertebrado", "mamifero", "herbivoro"), "vaca",
                    Arrays.asList("invertebrado", "inseto", "hematofago"), "pulga",
                    Arrays.asList("invertebrado", "inseto", "herbivoro"), "lagarta",
                    Arrays.asList("invertebrado", "anelideo", "hematofago"), "sanguessuga",
                    Arrays.asList("invertebrado", "anelideo", "onivoro"), "minhoca"
            );
            String value = animals.keySet().stream()
                    .filter(key -> new HashSet<>(key).containsAll(type)).findFirst()
                    .map(animals::get).orElse(messages.get("question.1049.error", language));
            return sb.append(value).toString();
        } catch (
                Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return messages.get("error.parse.input", language);
        }
    }

}
