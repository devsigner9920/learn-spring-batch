package io.dvsgn.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public HelloJobConfiguration(JobBuilderFactory jobBuilderFactory,
                                 StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2())
                .build();
    }

    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet(((contribution, chunkContext) -> {
                    System.out.println("==================");
                    System.out.println("Hello Spring Batch");
                    System.out.println("==================");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")
                .tasklet(((contribution, chunkContext) -> {
                    System.out.println("==================");
                    System.out.println("Second Step Start!");
                    System.out.println("==================");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
