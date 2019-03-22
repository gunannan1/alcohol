package com.app.alcohol.enums;


/**
 * result messages
 */
public enum  ResultEnum {

    SUCCESS(200, "success"),
    Error(500, "system error"),


    //register or login failure message
    Register_Failed(500,"register failed"),
    Empty_Username(500,"Username can't be empty"),
    Empty_Password(500,"Password can't be empty"),
    Empty_Email(500,"Email can't be empty"),
    Repeated_Username(500,"Username is repeated"),
    Login_Failed(500,"login failed,wrong username or password");





    private int status;
    private String message;

    private ResultEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }


    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
