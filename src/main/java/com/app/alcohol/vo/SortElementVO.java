package com.app.alcohol.vo;

import lombok.Data;

@Data
public class SortElementVO {
    /**
     * gap description in rank
     */
    String gap;
    /**
     * gap id in rank
     */
    Integer gapId;
    /**
     *  percentage of this gap in rank
     */
    Double percentage;
}
