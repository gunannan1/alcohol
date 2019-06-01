package com.app.alcohol.vo;

import lombok.Data;

/**
 * Return to app end for user to choose researcher
 */
@Data
public class ResearcherChooseVO {
    /**
     * username
     */
    private String username;

    /**
     * first name
     */
    private String firstName;

    /**
     * last name
     */
    private String lastName;

    /**
     * unique researcher id
     */
    private String researcherId;

}
