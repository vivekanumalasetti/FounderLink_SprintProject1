package com.founderlink.investmentservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "founderlink.exchange";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }
}