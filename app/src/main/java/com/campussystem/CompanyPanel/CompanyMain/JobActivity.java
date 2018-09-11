package com.campussystem.CompanyPanel.CompanyMain;

/**
 * Created by User on 3/14/2018.
 */

public class JobActivity {
    String jId;
    String id;
    String job;
    String jobType;
    String jobExperience;
    String jobShift;
    String jobSalary;

    public JobActivity() {
    }


    public JobActivity(String jId,String id, String job, String jobType, String jobExperience, String jobShift, String jobSalary) {
        this.id = id;
        this.job = job;
        this.jobType = jobType;
        this.jobExperience = jobExperience;
        this.jobShift = jobShift;
        this.jobSalary = jobSalary;
        this.jId=jId;
    }

    public String getjId() {
        return jId;
    }

    public String getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getJobType() {
        return jobType;
    }

    public String getJobExperience() {
        return jobExperience;
    }

    public String getJobShift() {
        return jobShift;
    }

    public String getJobSalary() {
        return jobSalary;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setJobExperience(String jobExperience) {
        this.jobExperience = jobExperience;
    }

    public void setJobShift(String jobShift) {
        this.jobShift = jobShift;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public void setjId(String jId) {
        this.jId = jId;
    }
}
