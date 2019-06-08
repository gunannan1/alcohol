package com.app.alcohol.vo;

import lombok.Data;

@Data
public class ResearcherVO {
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
     * email address
     */
    private String email;

    /**
     * unique researcher id
     */
    private String researcherId;

    /**
     * access token for dropbox
     */
    private String accessToken;






}
