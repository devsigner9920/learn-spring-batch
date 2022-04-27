package io.dvsgn.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionRunner implements ApplicationRunner {
    private final JobLauncher jobLauncher;
    private final Job job;

    public JobExecutionRunner(JobLauncher jobLauncher, Job jobExecutionTest) {
        this.jobLauncher = jobLauncher;
        this.job = jobExecutionTest;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jobLauncher.run(job,
                new JobParametersBuilder()
                .addString("arg", "1")
                .toJobParameters());
    }
}
