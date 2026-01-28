package com.my.answers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    private Instant createdAt;

    private Instant updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @PrePersist
    public void prePersist() {

        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}
