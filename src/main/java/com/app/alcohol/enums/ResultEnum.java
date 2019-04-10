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
    Login_Failed(500,"login failed,wrong username or password"),




    Write_Error(500,"Write Error"),
    TOKEN_EXPIRED(700, "token expired"),
    TOKEN_ERROR(700, "token validate error");


    private int code;
    private String message;

    private ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
