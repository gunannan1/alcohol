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
     * 0 - practice
     * 1,2,3,4- the 1,2,3,4 experiment
     */
    private Integer block;


    /**
     * in practice the num is 10
     * in formal experiment the num is 20
     */
    private Integer trials;


    /**
     *num of incorrect response of the test
     */
    private Integer incorrect;


    /**
     *num of missed response of the test
     */
    private Integer missed;

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
