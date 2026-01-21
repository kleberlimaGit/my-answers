package com.my.answers.repository;

import com.my.answers.entity.Answer;
import com.my.answers.entity.Language;
import com.my.answers.entity.dto.AnswerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT new com.my.answers.entity.dto.AnswerResponse(a.question, a.snippet, a.questionNumber) FROM Answer a " +
            "WHERE LOWER(a.dataType) LIKE LOWER(CONCAT('',:dataType,'%')) AND a.language = :language")
    Page<AnswerResponse> findAllPaged(PageRequest pageRequest, String dataType, Language language);

    boolean existsByLanguageAndQuestion(String language, Integer questionNumber);
}
