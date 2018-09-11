package com.campussystem;

/**
 * Created by User on 3/7/2018.
 */

public class UserActivity {
    String id;
    String userName;
    String genere;

    public UserActivity() {
    }

    public UserActivity(String id, String userName, String genere) {
        this.id = id;
        this.userName = userName;
        this.genere = genere;
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


    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
