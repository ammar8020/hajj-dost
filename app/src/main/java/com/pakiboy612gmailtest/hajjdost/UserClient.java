package com.pakiboy612gmailtest.hajjdost;

import android.app.Application;

import com.pakiboy612gmailtest.hajjdost.models.User;


public class UserClient extends Application {

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
