package com.example.lab4.Responses;

public class ForgotResponses {
    private boolean status;

    public ForgotResponses() {

    }

    public ForgotResponses(boolean status, boolean result) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
