package com.foreignexchange.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_date", columnDefinition = "DATE")
    private LocalDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "DATE")
    private LocalDateTime updatedDate;

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
            createdDate = LocalDateTime.now();
        }

        updatedDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
