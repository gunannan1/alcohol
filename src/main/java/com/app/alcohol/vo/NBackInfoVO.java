package com.app.alcohol.vo;

import lombok.Data;

import java.util.List;

@Data
public class NBackInfoVO {
    /**
     * nback correctness history list of a user
     */
    private List<Double> records;

}
