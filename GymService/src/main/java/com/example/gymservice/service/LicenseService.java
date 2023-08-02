package com.example.gymservice.service;

import com.example.gymservice.domain.license.License;
import com.example.gymservice.dto.license.LicenseDto;
import com.example.gymservice.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LicenseService {
    private final LicenseRepository repository;

    public LicenseDto saveLicense(License entity) {
        repository.save(entity);
        return LicenseDto.from(entity);
    }
}
