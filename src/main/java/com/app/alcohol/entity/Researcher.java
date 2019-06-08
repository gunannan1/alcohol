package com.app.alcohol.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * Researcher entity
 */
@Data
@TableName("researcher")
public class Researcher {
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
     * unique researcher id
     */
    private String researcherId;

    /**
     * access token for dropbox
     */
    private String accessToken;

    /**
     * time to create
     */
    private Date createTime;





}
