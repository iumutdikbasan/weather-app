package com.iumutdikbasan.weatherapp.result.generalResult;

public class Result {

    private boolean isSuccess;

    private String message;

    public Result(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}