package com.app.alcohol.vo;

import lombok.Data;

@Data
public class NBackRecordVO {

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
    private float percentage;

}
