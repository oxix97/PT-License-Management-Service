package com.example.gymservice.repository;

import com.example.gymservice.domain.license.LicenseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface LicenseRepository extends JpaRepository<LicenseEntity, Integer> {
    List<LicenseEntity> findByCreatedAtAfter(LocalDateTime time, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update LicenseEntity l " +
            "set l.count = :count, " +
            "l.period = :period " +
            "where l.licenseSeq = :licenseSeq")
    int updateCountAndPeriod(Integer licenseSeq, Integer count, Integer period);
}