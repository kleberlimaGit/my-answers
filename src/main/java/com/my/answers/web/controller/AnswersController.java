package com.my.answers.web.controller;

import com.my.answers.entity.Language;
import com.my.answers.entity.dto.AnswerResponse;
import com.my.answers.entity.dto.QuestionRequest;
import com.my.answers.resolver.QuestionServiceResolver;
import com.my.answers.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import static com.my.answers.utils.Utils.toLocale;

@Controller
@RequestMapping("/{lang}/answers")
@RequiredArgsConstructor
public class AnswersController {

    private final QuestionServiceResolver resolver;
    private final LocaleResolver localeResolver;
    private final AnswerService answerService;
    private final MessageSource messageSource;

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
        model.addAttribute("inputSizeError", messageSource.getMessage("app.input.size.error", null, toLocale(Language.getInstance(lang))));
        return "index";
    }

}