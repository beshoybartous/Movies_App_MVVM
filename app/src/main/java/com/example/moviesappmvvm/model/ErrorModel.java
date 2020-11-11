package com.example.moviesappmvvm.model;

public class ErrorModel extends Exception{

    int errorId;
    String errorMsg;

    public ErrorModel() {
    }

    public ErrorModel(int errorId, String errorMsg) {
        this.errorId = errorId;
        this.errorMsg = errorMsg;
    }
}