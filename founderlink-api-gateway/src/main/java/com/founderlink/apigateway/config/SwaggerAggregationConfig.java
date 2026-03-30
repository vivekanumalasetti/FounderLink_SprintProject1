package com.founderlink.apigateway.config;

import jakarta.annotation.PostConstruct;

import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties.SwaggerUrl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SwaggerServicesProperties.class)
public class SwaggerAggregationConfig {

    private final SwaggerUiConfigParameters swaggerUiConfigParameters;
    private final SwaggerServicesProperties swaggerServicesProperties;

    public SwaggerAggregationConfig(SwaggerUiConfigParameters swaggerUiConfigParameters,
                                    SwaggerServicesProperties swaggerServicesProperties) {
        this.swaggerUiConfigParameters = swaggerUiConfigParameters;
        this.swaggerServicesProperties = swaggerServicesProperties;
    }

    @PostConstruct
    public void configureSwaggerUi() {
        swaggerUiConfigParameters.clearUrls();
        swaggerServicesProperties.getServices().forEach(service -> {
            SwaggerUrl swaggerUrl = new SwaggerUrl();
            swaggerUrl.setName(service.getName());
            swaggerUrl.setUrl(service.getUrl());
            swaggerUiConfigParameters.addUrl(swaggerUrl);
        });
    }
}

