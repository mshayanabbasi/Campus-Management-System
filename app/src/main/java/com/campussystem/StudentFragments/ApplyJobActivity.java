package com.campussystem.StudentFragments;

/**
 * Created by User on 3/14/2018.
 */

public class ApplyJobActivity {
    String compId;
    String uid;
    String name;
    String qualification;
    String university;
    String jId;
    String appId;
    String status;

    public ApplyJobActivity() {
    }

    public ApplyJobActivity(String compId, String uid, String name, String qualification, String university, String jId, String appId,String status) {
        this.compId = compId;
        this.uid = uid;
        this.name = name;
        this.qualification = qualification;
        this.university = university;
        this.jId = jId;
        this.appId = appId;
        this.status=status;
    }

    public String getCompId() {
        return compId;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getQualification() {
        return qualification;
    }

    public String getUniversity() {
        return university;
    }

    public String getjId() {
        return jId;
    }

    public String getAppId() {
        return appId;
    }

    public String getStatus() {
        return status;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setjId(String jId) {
        this.jId = jId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
