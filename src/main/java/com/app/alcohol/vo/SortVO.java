package com.app.alcohol.vo;

import lombok.Data;

import java.util.List;

@Data
public class SortVO {
    List<Double> list;
    Double myPercentage;
    Integer myGapId;
}
