package com.ogoma.dynamicworkflow.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "dynamic.workflow")
@Getter
@Setter
@Configuration
public class AppProperties {
    private String temporalAddress;
    private String temporalNamespace;
    private String taskQueue;
}
