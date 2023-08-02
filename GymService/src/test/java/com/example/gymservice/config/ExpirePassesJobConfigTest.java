package com.example.gymservice.config;

import com.example.gymservice.domain.pass.PassData;
import com.example.gymservice.domain.pass.PassStatus;
import com.example.gymservice.repository.PassDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {ExpirePassesJobConfig.class, TestBatchConfig.class})
public class ExpirePassesJobConfigTest {
    private final JobLauncherTestUtils jobLauncherTestUtils;
    private final PassDataRepository passDataRepository;

    public ExpirePassesJobConfigTest(
            @Autowired JobLauncherTestUtils jobLauncherTestUtils,
            @Autowired PassDataRepository passDataRepository
    ) {
        this.jobLauncherTestUtils = jobLauncherTestUtils;
        this.passDataRepository = passDataRepository;
    }

    @Test
    public void test_expirePassesStep() throws Exception {
        // given
        addPassEntities(10);

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance jobInstance = jobExecution.getJobInstance();

        // then
        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        assertEquals("expirePassesJob", jobInstance.getJobName());
    }

    private void addPassEntities(int size) {
        final LocalDateTime now = LocalDateTime.now();
        final Random random = new Random();

        List<PassData> passEntities = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            PassData passData = PassData.of(
                    1,
                    "A" + 3030 + i,
                    PassStatus.PROGRESSED,
                    random.nextInt(11),
                    now.minusDays(60),
                    now.minusDays(1)
            );
            passEntities.add(passData);
        }
        passDataRepository.saveAll(passEntities);
    }
}
