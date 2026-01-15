package com.example.Job.App.job;

import com.example.Job.App.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    //  private final List<Job> jobs = new ArrayList<>();

    @GetMapping("/id")
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJobs(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {

//        JobController jobService = null;
        Job job = jobService.getJobById(id);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
//    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if(updated)
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }








/*//    @RestController
//  @RequestMapping("/jobs")
//    public class JobController {

//        private final JobService jobService;
//
//        public JobController(JobService jobService) {
//            this.jobService = jobService;
//        }

        @GetMapping
        public ResponseEntity<List<Job>> findAll() {
            return ResponseEntity.ok(jobService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Job> getJobById(@PathVariable Long id) {
            Job job = jobService.getJobById(id);
            if (job == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(job);
        }

        @PostMapping
        public ResponseEntity<String> createJob(@RequestBody Job job) {
            jobService.createJobs(job);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Job added successfully");
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> updateJob(
                @PathVariable Long id,
                @RequestBody Job updatedJob) {

            if (!jobService.updateJob(id, updatedJob))
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok("Job updated successfully");
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteJob(@PathVariable Long id) {
            if (!jobService.deleteJobById(id))
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok("Job deleted successfully");
        }
//    }*/

}
