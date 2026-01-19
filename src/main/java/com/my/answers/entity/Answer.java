package com.my.answers.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j;

@Entity
@Table(name = "tb_answer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j
public class Answer extends AbstractAuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String dataType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String snippet;

    @Column(nullable = false)
    private Integer questionNumber;

}

