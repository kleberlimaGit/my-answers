package com.my.answers.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.Instant;

@MappedSuperclass
@Data
public abstract class AbstractAuditEntity {

    private Instant createdAt;

    private Instant updatedAt;

    private String usuario;

    @PrePersist
    public void prePersist() {

        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}
