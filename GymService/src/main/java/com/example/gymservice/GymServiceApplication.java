package com.example.gymservice;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
@SpringBootApplication
public class GymServiceApplication {

    private final JobRepository jobRepository;

    public static void main(String[] args) {
        SpringApplication.run(GymServiceApplication.class, args);
    }

    @Bean
    public Job passJob(Step step) {
        return new JobBuilder("myJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step passStep(JobRepository jobRepository, Tasklet myTasklet, PlatformTransactionManager manager) {
        return new StepBuilder("passStep", jobRepository)
                .tasklet(myTasklet, manager)
                .build();
    }

    @Bean
    public Tasklet myTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Execute PassStep");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
