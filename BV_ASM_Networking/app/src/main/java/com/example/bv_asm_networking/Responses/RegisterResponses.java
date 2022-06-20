package com.example.bv_asm_networking.Responses;

public class RegisterResponses {
    private boolean status;
    private boolean result;

    public RegisterResponses() {

    }

    public RegisterResponses(boolean status, boolean result) {
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
