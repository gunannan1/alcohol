package com.app.alcohol.vo;

import lombok.Data;

@Data
public class PasswordVO {
    /**
     * new password
     */
    private String newPassword;

    /**
     * confirm new password
     */
    private String confirmPassword;
}
