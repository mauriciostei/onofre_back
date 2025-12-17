package com.onofre.store.adams.infraestructure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "adams")
@Data
public class AdamsProperties {
    private String url;
    private String apikey;
//    private String payDurationMinutes;
}
