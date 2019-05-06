package com.app.alcohol.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

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
    private Integer sex;

    /**
     * age
     */
    private Integer age;

    /**
     * unique researcher id
     */
    private String researcherId;

    /**
     * time to register
     */
    private Date createTime;






}
