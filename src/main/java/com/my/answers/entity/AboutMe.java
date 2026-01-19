package com.my.answers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.log4j.Log4j;

import java.util.UUID;

@Entity
@Table(name = "tb_about_me")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j
public class AboutMe extends AbstractAuditEntity{

    @Id
    private UUID uuid = UUID.randomUUID();
    @Column(nullable = false, columnDefinition = "TEXT")
    private String curriculum;
    @Column(nullable = false, length = 4)
    private String country;
}
