package com.example.componentProcessing.model;

public class CompleteResponse {
    private int flag;

    public CompleteResponse() {
    }

    public CompleteResponse(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "CompleteResponse{" +
                "flag=" + flag +
                '}';
    }
}
