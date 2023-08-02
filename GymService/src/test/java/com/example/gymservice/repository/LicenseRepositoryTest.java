package com.example.gymservice.repository;

import com.example.gymservice.domain.license.License;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class LicenseRepositoryTest {
    private final LicenseRepository licenseRepository;

    public LicenseRepositoryTest(@Autowired LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @DisplayName("이용권 저장")
    @Test
    void test1() {
        //given
        License entity = createdPackageEntity(1, "챌린지 12", 0, 84);

        //when
        licenseRepository.save(entity);

        //then
        assertThat(licenseRepository.getReferenceById(1)).isNotNull();
    }

    @DisplayName("이용권 리스트 출력")
    @Test
    void test2() {
        //given
        LocalDateTime time = LocalDateTime.now().minusMinutes(1);
        License p1 = createdPackageEntity(1, "챌린지11", 0, 80);
        License p2 = createdPackageEntity(2, "챌린지12", 0, 90);
        License p3 = createdPackageEntity(3, "챌린지13", 0, 100);
        licenseRepository.save(p1);
        licenseRepository.save(p2);
        licenseRepository.saveAndFlush(p3);

        //when
        List<License> entities = licenseRepository.findByCreatedAtAfter(time, PageRequest.of(0, 5, Sort.by("licenseSeq")));

        //then
        assertThat(entities)
                .isNotNull()
                .hasSize(3);
    }

    @DisplayName("이용권 수정")
    @Test
    void test3() {
        //given
        License entity = License.of(1, "챌린지 1", 0, 80);
        licenseRepository.save(entity);

        //when
        int count = licenseRepository.updateCountAndPeriod(1, 5, 100);

        //then
        License expected = licenseRepository.findById(1).orElseThrow();

        assertEquals(1, count);
        assertThat(expected)
                .hasFieldOrPropertyWithValue("count", 5)
                .hasFieldOrPropertyWithValue("period", 100);

    }

    @DisplayName("이용권 제거")
    @Test
    void test4() {
        //given
        int preSize = licenseRepository.findAll().size();
        //when
        licenseRepository.deleteById(1);

        //then
        assertEquals(preSize - 1, licenseRepository.findAll().size());
    }

    private License createdPackageEntity(Integer licenseSeq, String name, int count, int period) {
        return License.of(
                licenseSeq,
                name,
                count,
                period
        );
    }
}