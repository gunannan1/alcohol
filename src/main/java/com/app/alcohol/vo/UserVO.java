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
     * sex 0-male，1-female
     */
    private Integer sex;

    /**
     * age
     */
    private Integer age;


    /**
     * unique researcher id
     */
    private String researcherId;
}
