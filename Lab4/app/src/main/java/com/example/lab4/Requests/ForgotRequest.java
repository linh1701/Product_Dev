package com.example.lab4.Requests;

public class ForgotRequest {
    private String email;

    public ForgotRequest(){}

    public ForgotRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
