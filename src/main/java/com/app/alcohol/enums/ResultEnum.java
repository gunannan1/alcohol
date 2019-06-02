package com.app.alcohol.enums;


/**
 * result messages
 */
public enum  ResultEnum {

    SUCCESS(200, "success"),
    Error(500, "system error"),


    //register or login failure message
    Register_Failed(500,"Register failed"),
    Empty_Username(500,"Username can't be empty"),
    Empty_Password(500,"Password can't be empty"),
    Empty_Email(500,"Email can't be empty"),
    Repeated_Username(500,"Username is repeated"),
    Repeated_Email(500,"Email is repeated"),
    Login_Failed(500,"Login failed,wrong username or password"),
    NO_UPLOAD_TOKEN(500,"No token for upload the file"),
    Passworrd_Not_Match(500,"Confirm password don't match"),
    Wrong_Email(500,"The email address does not exist"),
    Send_Email_Error(500,"Error when sending email"),
    User_Not_Exist(500,"User not exist"),
    Researcher_Not_Exist(500,"Researcher not exist"),
    Write_Error(500,"Write Error"),
    TOKEN_EXPIRED(401, "token expired"),
    TOKEN_ERROR(401, "token validate error");


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
