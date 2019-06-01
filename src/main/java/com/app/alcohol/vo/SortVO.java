package com.app.alcohol.vo;

import lombok.Data;

import java.util.List;

@Data
public class SortVO {
    /**
     * the percentage of every gap,
     * order from small to big (0,100),(100,200)..... like this
     */
    List<Double> list;

    /**
     * my score's rank percentage in total scores
     */
    Double myPercentage;

    /**
     * my socre
     */
    Double myScore;

    /**
     * my rank's position int the list
     */
    Integer myGapPosition;
}
