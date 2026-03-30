package com.founderlink.apigateway.config;

import jakarta.annotation.PostConstruct;

import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigParameters.SwaggerUrl;
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
        swaggerServicesProperties.getServices().forEach(service -> {
            SwaggerUrl swaggerUrl = new SwaggerUrl(service.getName(), service.getUrl(), null);
            swaggerUiConfigParameters.addUrl(swaggerUrl);
        });
    }
}
