package com.app.alcohol.vo;

import lombok.Data;

/**
 * used in get sst history records
 */
@Data
public class SSTInfoVO {
    private Integer incorrect;
    private Integer missed;
    private Double percentage;
    private Double reactionTime;

}
