package com.app.alcohol.vo;

import lombok.Data;

import java.util.List;

/**
 * used in generating n-back
 */
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
