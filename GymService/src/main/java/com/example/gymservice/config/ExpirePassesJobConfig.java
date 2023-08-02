package com.example.gymservice.config;

import com.example.gymservice.domain.pass.PassData;
import com.example.gymservice.domain.pass.PassStatus;
import com.example.gymservice.service.PassDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ExpirePassesJobConfig extends DefaultBatchConfiguration {
    @Value("${chunkSize:1000}")
    private int getChunkSize;
    private final PassDataService service;
    @Bean
    public Job expirePassesJob(
            final JobRepository jobRepository,
            final Step expirePassesStep
    ) {
        return new JobBuilder("expirePassesJob", jobRepository)
                .start(expirePassesStep)
                .build();
    }

    @Bean
    public Step expirePassesStep(
        final JobRepository jobRepository,
        final PlatformTransactionManager transactionManager
    ) {
        return new StepBuilder("expirePassesStep", jobRepository)
                .chunk(this.getChunkSize,transactionManager)
                .reader(this.expirePassesItemReader)
                .processor(this.expirePassesItemProcessor)
                .writer(this.expirePassesItemWriter)
                .build();
    }

//    @Bean
//    @StepScope
//    public JpaCursorItemReader<PassData> expirePassesItemReader() {
//        return new JpaCursorItemReaderBuilder<PassData>()
//                .name("expirePassesItemReader")
//                .entityManagerFactory(entityManagerFactory)
//                .queryString("select p from PassData p where p.status = :status and p.endedAt <= :endedAt")
//                .parameterValues(Map.of("status", PassStatus.PROGRESSED, "endedAt", LocalDateTime.now()))
//                .build();
//    }

    private ListItemReader<PassData> expirePassesItemReader() {
        return new ListItemReader<>()(this.service)
    }

    @Bean
    public ItemProcessor<PassData, PassData> expirePassesItemProcessor() {
        return item -> {
            item.setStatus(PassStatus.EXPIRED);
            item.setExpiredAt(LocalDateTime.now());
            return item;
        };
    }

    @Bean
    public ItemWriter<Object> expirePassesItemWriter() {
        return new JpaItemWriterBuilder<PassData>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
