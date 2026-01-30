package com.my.answers.web.controller;

import com.my.answers.entity.dto.AnswerResponse;
import com.my.answers.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{lang}/answers")
@RequiredArgsConstructor
public class AnswersController {

    private final AnswerService answerService;

    @GetMapping
    public String listar(ModelMap model,
                         @RequestParam(value = "dataType", defaultValue = "") String dataType,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @PathVariable String lang

    ) {
        PageRequest pageRequest = PageRequest.of(page, 1, Sort.Direction.valueOf("ASC"), "questionNumber");
        Page<AnswerResponse> pageAnswers = answerService.findAllPaged(pageRequest, dataType, lang);
        model.addAttribute("pageAnswers", pageAnswers);
        model.addAttribute("lang", lang);
        return "index";
    }

}