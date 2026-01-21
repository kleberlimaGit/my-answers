package com.my.answers.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class Question1021 implements QuestionService {

    @Override
    public int questionNumber() {
        return 1021;
    }

    @Override
    public String response(String data) {
        try {
            double value = Double.parseDouble(data);
            StringBuilder sb = new StringBuilder();
            List<Integer> notes = List.of(100, 50, 20, 10, 5, 2);
            List<Double> coins = List.of(1.0, 0.5, 0.25, 0.1, 0.05, 0.01);
            sb.append("NOTES:\n");
            for (Integer note : notes) {

                value = Math.round(value * 100.0) / 100.0;
                int quantity = (int) (value / note);
                value %= note;
                sb.append(String.format("%d note(s) of R$ %.2f%n", quantity, (double) note));
            }
            sb.append("COINS:\n");
            for (double coin : coins) {

                value = Math.round(value * 100.0) / 100.0;
                int quantity = (int) (value / coin);
                value %= coin;
                sb.append(String.format("%d coin(s) of R$ %.2f%n", quantity, coin));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return "Error parsing input data";
        }
    }
}
