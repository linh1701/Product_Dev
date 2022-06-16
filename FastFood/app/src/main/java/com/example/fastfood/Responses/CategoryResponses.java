package com.example.fastfood.Responses;

public class CategoryResponses {
    private boolean status;
    private boolean result;

    public CategoryResponses() {

    }

    public CategoryResponses(boolean status, boolean result) {
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
