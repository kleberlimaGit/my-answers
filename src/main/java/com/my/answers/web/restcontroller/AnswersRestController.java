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

//    @GetMapping("{lang}/page")
//    public ResponseEntity<Page<AnswerResponse>> findAll(
//            @RequestParam(value = "dataType", defaultValue = "") String dataType,
//            @RequestParam(value = "language", defaultValue = "pt") String language,
//            @PathVariable String lang
//
//    ){
//        PageRequest pageRequest = PageRequest.of(0, 1, Sort.Direction.valueOf("ASC"), "questionNumber");
//        Page<AnswerResponse> answers = answerService.findAllPaged(pageRequest, dataType, language);
//        return ResponseEntity.ok(answers);
//    }

    @PostMapping
    public String get(@RequestBody QuestionRequest data, @PathVariable String lang) {
        return resolver.resolve(data.questionNumber()).response(data.request(), Language.getInstance(lang));
    }

}
