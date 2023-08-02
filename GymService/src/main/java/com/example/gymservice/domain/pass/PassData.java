package com.example.gymservice.domain.pass;

import com.example.gymservice.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString(callSuper = true)
@Getter
@Setter
@Table(name = "pass_data")
@Entity
public class PassData extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passSeq;

    private Integer licenseSeq;
    private String userId;

    @Enumerated(EnumType.STRING)
    private PassStatus status;
    private Integer remainingCount;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime expiredAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassData)) return false;
        PassData that = (PassData) o;
        return Objects.equals(passSeq, that.passSeq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passSeq);
    }

    protected PassData() {
    }

    private PassData(
            Integer passSeq,
            Integer licenseSeq,
            String userId,
            PassStatus status,
            Integer remainingCount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            LocalDateTime expiredAt
    ) {
        this.passSeq = passSeq;
        this.licenseSeq = licenseSeq;
        this.userId = userId;
        this.status = status;
        this.remainingCount = remainingCount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.expiredAt = expiredAt;
    }

    public static PassData of(
            Integer passSeq,
            Integer packageSeq,
            String userId,
            PassStatus status,
            Integer remainingCount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            LocalDateTime expiredAt
    ) {
        return new PassData(
                passSeq,
                packageSeq,
                userId,
                status,
                remainingCount,
                startedAt,
                endedAt,
                expiredAt
        );
    }

    public static PassData of(
            Integer packageSeq,
            String userId,
            PassStatus status,
            Integer remainingCount,
            LocalDateTime startedAt,
            LocalDateTime endedAt
    ) {
        return PassData.of(
                null,
                packageSeq,
                userId,
                status,
                remainingCount,
                startedAt,
                endedAt,
                null
        );
    }
}
