package com.my.answers.service.questions;

import com.my.answers.entity.Language;
import com.my.answers.service.MessageProvider;
import com.my.answers.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class Question1048 implements QuestionService {

    private final MessageProvider messages;

    @Override
    public int questionNumber() {
        return 1048;
    }

    @Override
    public String response(List<String> data, Language language) {
        try {
            double salary = Double.parseDouble(data.get(0).trim());
            double percentage = 0.0;
            double increase = 0.0;
            double newSalary = 0.0;
            StringBuilder sb = new StringBuilder();
            Map<String, Double> salaryIncreaseMap = Map.of(
                    "0 - 400.00",0.15,
                    "400.01 - 800.00",0.12,
                    "800.01 - 1200.00",0.10,
                    "1200.01 - 2000.00", 0.07
            );

            for(Map.Entry<String, Double> entry : salaryIncreaseMap.entrySet()) {
                percentage = entry.getValue();
                String[] limits = entry.getKey().split(" - ");
                double lowerLimit = Double.parseDouble(limits[0].trim());
                double upperLimit = Double.parseDouble(limits[1].trim());

                if(salary >= lowerLimit && salary <= upperLimit) {
                    increase = salary * percentage;
                    newSalary = salary + increase;
                    break;
                }
            }
            if(newSalary == 0.0){
                percentage = 0.04;
                increase = salary * percentage;
                newSalary = salary + increase;
            }
            sb.append(String.format("Novo salario: %.2f%n", newSalary));
            sb.append(String.format("Reajuste ganho: %.2f%n", increase));
            sb.append(String.format("Em percentual: %d %%", (int)(percentage * 100)));
            return sb.toString();
        } catch (Exception e) {
            log.error("Error parsing input data: {}", data, e);
            return messages.get("error.parse.input", language);
        }
    }

}
