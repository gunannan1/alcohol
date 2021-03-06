package com.app.alcohol.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  jwt configuration
 */
@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtProperties {

    private String tokenHeader ;

    private String secret;

    private Long expiration;

    private String tokenHead;

    private String md5Key;

    /**
     * url that need intercept
     */
    private String interceptUrl;

    /**
     * url that need ignore, these url often has same start with interceptUrl.
     */
    private String ignoreUrl;

}
