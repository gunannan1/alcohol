package com.app.alcohol.vo;

import lombok.Data;

@Data
public class NBackRecordVO {

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


}
