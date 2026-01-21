package com.my.answers.service;

import com.my.answers.entity.Answer;
import com.my.answers.entity.Language;
import com.my.answers.entity.dto.AnswerRequest;
import com.my.answers.entity.dto.AnswerResponse;
import com.my.answers.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private static final Logger log = LogManager.getLogger(AnswerService.class);
    private final AnswerRepository answerRepository;

    public void save(AnswerRequest request) {
        if(answerRepository.existsByLanguageAndQuestion(request.language(), request.questionNumber())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Answer already exists");
        }
        Answer answer = Answer.builder().
                dataType(request.dataType())
                .question(request.question())
                .snippet(request.snippet())
                .language(Language.getInstance(request.language()))
                .questionNumber(request.questionNumber())
                .build();

        saveOrUpdate(answer);
    }

    public void edit(Long id, AnswerRequest request) {
        Answer answer = Answer.builder().id(id).
                dataType(request.dataType())
                .question(request.question())
                .snippet(request.snippet())
                .language(Language.getInstance(request.language()))
                .questionNumber(request.questionNumber())
                .build();
        saveOrUpdate(answer);
    }

    public Page<AnswerResponse> findAllPaged(PageRequest pageRequest, String dataType, String language) {
        Language lang = Language.getInstance(language);
        return answerRepository.findAllPaged(pageRequest, dataType, lang);
    }

    private void saveOrUpdate(Answer answer) {
        try {
            answerRepository.save(answer);
        }catch (DataIntegrityViolationException e){
            log.error("Error saving answer: {}", answer, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error saving answer");
        }catch (Exception e){
            log.error("Error saving answer: {}", answer, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving answer");
        }
    }
}
