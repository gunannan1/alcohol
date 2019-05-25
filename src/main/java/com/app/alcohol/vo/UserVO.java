package com.app.alcohol.vo;


import lombok.Data;

@Data
public class UserVO {

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * first name
     */
    private String firstName;

    /**
     * last name
     */
    private String lastName;

    /**
     * email address
     */
    private String email;

    /**
     * gender 0-male，1-female
     */
    private Integer gender;

    /**
     * age
     */
    private Integer age;


    /**
     * unique researcher id
     */
    private String researcherId;
}
