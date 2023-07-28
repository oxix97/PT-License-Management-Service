package com.example.gymservice.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString
@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(updatable = false, nullable = false)
    protected LocalDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    protected LocalDateTime modifiedAt;
}
