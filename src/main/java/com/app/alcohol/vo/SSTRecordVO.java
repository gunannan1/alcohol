package com.app.alcohol.vo;

import lombok.Data;

@Data
public class SSTRecordVO {
    /**
     * username of user who attend this test
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
     *Mean reaction time to go stimuli
     */
    private float reactionTime;

    /**
     *Percentage of correctly suppressed responses on stop trials
     */
    private float percentage;
}
