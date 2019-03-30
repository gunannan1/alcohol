package com.app.alcohol.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

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
     * unique researcher id
     */
    private String researcherID;

    /**
     * access token for dropbox
     */
    private String accessToken;


}