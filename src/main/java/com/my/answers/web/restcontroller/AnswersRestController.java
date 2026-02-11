package com.my.answers.web.restcontroller;

import com.my.answers.entity.Language;
import com.my.answers.entity.dto.AnswerResponse;
import com.my.answers.entity.dto.QuestionRequest;
import com.my.answers.resolver.QuestionServiceResolver;
import com.my.answers.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

@RestController
@RequestMapping("/{lang}/answers")
@RequiredArgsConstructor
public class AnswersRestController {

    private final QuestionServiceResolver resolver;
    private final LocaleResolver localeResolver;
    private final AnswerService answerService;

    @PostMapping
    public String get(@RequestBody QuestionRequest data, @PathVariable String lang) {
        return resolver.resolve(data.questionNumber()).response(data.request(), Language.getInstance(lang));
    }

    @GetMapping("page")
    public ResponseEntity<Page<AnswerResponse>> getPageRest(
            @RequestParam(value = "dataType", defaultValue = "") String dataType,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @PathVariable String lang

    ) {
        dataType = dataType.isBlank() ? null : dataType;
        PageRequest pageRequest = PageRequest.of(page, 1, Sort.Direction.ASC, "questionNumber");
        Page<AnswerResponse> pageAnswers = answerService.findAllPaged(pageRequest, dataType, lang);
        return ResponseEntity.ok(pageAnswers);
    }
}
