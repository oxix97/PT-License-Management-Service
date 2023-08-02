package com.example.gymservice.dto.license;

import com.example.gymservice.domain.license.License;

import java.time.LocalDateTime;

public record LicenseDto(
        Integer licenseSeq,
        String licenseName,
        Integer count,
        Integer period,
        LocalDateTime modifiedAt,
        LocalDateTime createdAt
) {
    public static LicenseDto of(
            Integer licenseSeq,
            String licenseName,
            Integer count,
            Integer period,
            LocalDateTime modifiedAt,
            LocalDateTime createdAt
    ) {
        return new LicenseDto(
                licenseSeq,
                licenseName,
                count,
                period,
                modifiedAt,
                createdAt
        );
    }

    public static LicenseDto of(
            Integer licenseSeq,
            String licenseName,
            Integer count,
            Integer period
    ) {
        return LicenseDto.of(
                licenseSeq,
                licenseName,
                count,
                period,
                null,
                null
        );
    }

    public static LicenseDto from(License entity) {
        return new LicenseDto(
                entity.getLicenseSeq(),
                entity.getLicenseName(),
                entity.getCount(),
                entity.getPeriod(),
                entity.getModifiedAt(),
                entity.getCreatedAt()
        );
    }
}