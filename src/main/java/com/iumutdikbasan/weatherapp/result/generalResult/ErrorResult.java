package com.iumutdikbasan.weatherapp.result.generalResult;


public class ErrorResult extends Result{
    public ErrorResult(String message) {
        super(false, message);
    }

}