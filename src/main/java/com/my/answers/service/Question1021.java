package com.my.answers.service;

import com.my.answers.entity.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1021 implements QuestionService {

    private final MessageSource messageSource;

    @Override
    public int questionNumber() {
        return 1021;
    }

    @Override
    public String response(String data, Language language) {
        String _notes = switch (language) {
            case PT_BR -> "NOTAS:\n";
            case EN_US -> "NOTES:\n";
        };
        String _coins = switch (language) {
            case PT_BR -> "MOEDAS:\n";
            case EN_US -> "COINS:\n";
        };
        try {
            double value = Double.parseDouble(data.trim().replace(",", "."));
            StringBuilder sb = new StringBuilder();
            List<Integer> notes = List.of(100, 50, 20, 10, 5, 2);
            List<Double> coins = List.of(1.0, 0.5, 0.25, 0.1, 0.05, 0.01);
            sb.append(_notes);
            for (Integer note : notes) {

                value = Math.round(value * 100.0) / 100.0;
                int quantity = (int) (value / note);
                value %= note;
                sb.append(String.format("%d %s R$ %.2f%n", quantity,language.equals(Language.PT_BR) ? "nota(s) de" : "note(s) of" ,(double) note));
            }
            sb.append(_coins);
            for (double coin : coins) {

                value = Math.round(value * 100.0) / 100.0;
                int quantity = (int) (value / coin);
                value %= coin;
                sb.append(String.format("%d %s R$ %.2f%n", quantity,language.equals(Language.PT_BR) ? "moedas(s) de" : "coin(s) of" , coin));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            Locale locale = switch (language) {
                case PT_BR -> new Locale("pt", "BR");
                case EN_US -> Locale.US;
            };
            return messageSource.getMessage("error.parse.input", null, locale);
        }
    }
}
