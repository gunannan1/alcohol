package com.app.alcohol.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 *DDT-Record entity
 */
@Data
@TableName("DDT_Record")
public class DDTRecord {

    /**
     * id, auto increment
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * username of user who attend this test
     */
    private String username;


    /**
     * answer for question_1,0 means no answer,1 means choose 1 day, 2 means choose more days
     */
    private int question_1;

    private int question_2;

    private int question_3;

    private int question_4;

    private int question_5;

    private int question_6;

    private int question_7;

    private int question_8;

    private int question_9;

    private int question_10;

    private int question_11;

    private int question_12;

    private int question_13;

    private int question_14;

    private int question_15;

    private int question_16;

    private int question_17;

    private int question_18;

    private int question_19;

    private int question_20;

    private int question_21;

    private int question_22;

    private int question_23;

    private int question_24;

    private int question_25;

    private int question_26;

    private int question_27;

    /**
     * time to create this record
     */
    private Date createTime;








}
