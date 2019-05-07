package com.app.alcohol.vo;

import lombok.Data;

import java.util.List;

@Data
public class NBackResponseVO {

    /**
     * nback permutation
     */
    private List<String> permutation;

    /**
     * answers for nback
     */
    private List<Integer> answers;
}
