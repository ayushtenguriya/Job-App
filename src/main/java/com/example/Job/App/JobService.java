package com.example.Job.App;

import com.example.Job.App.job.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    void createJobs(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
