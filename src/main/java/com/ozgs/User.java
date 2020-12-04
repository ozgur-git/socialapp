package com.ozgs;

import javax.validation.constraints.NotEmpty;

public class User {

    @NotEmpty(message = "Not all fields were entered!")
    private String name;

    @NotEmpty(message = "Not all fields were entered!")
    private String password;

    private String userText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return super.toString();
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }
   
}