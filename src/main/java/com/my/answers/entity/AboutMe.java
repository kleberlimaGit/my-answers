package com.my.answers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Entity
@Table(name = "tb_about_me")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AboutMe extends AbstractEntity {

    @Id
    private UUID uuid = UUID.randomUUID();
    @Column(nullable = false, columnDefinition = "TEXT")
    private String curriculum;
}
