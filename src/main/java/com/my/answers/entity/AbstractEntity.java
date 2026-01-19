package com.my.answers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.Instant;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    private Instant createdAt;

    private Instant updatedAt;

    @Column(nullable = false, length = 4)
    private String country;

    @PrePersist
    public void prePersist() {

        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}
