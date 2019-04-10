package com.app.alcohol.vo;

import lombok.Data;

import java.util.List;

@Data
public class DDTRecordVO {
    /**
     * username of user who attend this test
     */
    private String username;


    /**
     * answer for question_1,0 means no answer,1 means choose 1 day, 2 means choose more days
     */
    private List<List<Integer>> answers;

}
