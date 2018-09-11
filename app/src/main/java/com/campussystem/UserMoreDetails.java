package com.campussystem;

/**
 * Created by User on 3/7/2018.
 */

public class UserMoreDetails {

    String id;
    String userName;
    String genere;
    String qualification;
    String gpa;
    String university;
    String email;
    String password;

    public UserMoreDetails() {

    }

    public UserMoreDetails(String id, String userName, String genere, String qualification, String gpa,String university,String email,String password) {
        this.id = id;
        this.userName = userName;
        this.genere = genere;
        this.qualification = qualification;
        this.gpa = gpa;
        this.university=university;
        this.email=email;
        this.password=password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUniversity() {
        return university;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getGenere() {
        return genere;
    }

    public String getQualification() {
        return qualification;
    }

    public String getGpa() {
        return gpa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
