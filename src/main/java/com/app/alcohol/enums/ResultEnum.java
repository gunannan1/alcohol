package com.app.alcohol.enums;


/**
 * result messages
 */
public enum  ResultEnum {

    SUCCESS(200, "success"),
    FAILD(500, "failure"),


    //register failure message
    Register_Failed(500,"register failed"),
    Empty_Username(500,"Username can't be empty"),
    Empty_Password(500,"Password can't be empty"),
    Empty_Email(500,"Email can't be empty"),
    Repeated_Username(500,"Username is repeated");



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
