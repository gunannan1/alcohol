package com.app.alcohol.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  Local file path and dropbox file path Configuration
 */
@Data
@ConfigurationProperties(prefix = "file")
@Component
public class FilePathConfig {
    public String localPrefix;

    public String dropboxPrefix;

}
