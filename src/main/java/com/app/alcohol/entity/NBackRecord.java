package com.app.alcohol.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 *NBack-Record entity
 */
@Data
@TableName("NBack_Record")
public class NBackRecord {
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
     * 1,2，3. mean the n back level
     */
    private Integer level;

    /**
     *accurate percentage
     */
    private Double percentage;

    /**
     * time to create this record
     */
    private Date createTime;


}
