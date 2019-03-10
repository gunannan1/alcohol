package com.app.alcohol.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * User entity
 */
@Data
@TableName("user")
public class User {

    /**
     * id, auto increment
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * name
     */
    private String name;

    /**
     * email address
     */
    private String email;

    /**
     * sex 0-male，1-female
     */
    private int sex;

    /**
     * age
     */
    private int age;





}
