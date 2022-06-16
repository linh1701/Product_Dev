package com.example.lab4.Responses;

public class ProductResponses {
    private boolean status;
    private boolean result;

    public ProductResponses() {

    }

    public ProductResponses(boolean status, boolean result) {
        this.status = status;
        this.result = result;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
