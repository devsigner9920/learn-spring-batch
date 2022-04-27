package io.dvsgn.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobInstanceRunner implements ApplicationRunner {
    private final JobLauncher jobLauncher;
    private final Job job;

    public JobInstanceRunner(JobLauncher jobLauncher, Job jobInstance) {
        this.jobLauncher = jobLauncher;
        this.job = jobInstance;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jobLauncher.run(job,
                new JobParametersBuilder()
                .addString("test", "test")
                .toJobParameters());
    }
}
