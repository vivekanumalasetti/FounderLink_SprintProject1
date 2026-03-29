package com.founderlink.notificationservice.config;

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

    @Bean
    Queue startupQueue() {
        return new Queue("startup.queue", true);
    }

    @Bean
    Queue investmentQueue() {
        return new Queue("investment.queue", true);
    }

    @Bean
    Queue teamQueue() {
        return new Queue("team.queue", true);
    }

    @Bean
    Binding startupBinding(Queue startupQueue, TopicExchange exchange) {
        return BindingBuilder.bind(startupQueue)
                .to(exchange)
                .with("startup.created");
    }

    @Bean
    Binding investmentBinding(Queue investmentQueue, TopicExchange exchange) {
        return BindingBuilder.bind(investmentQueue)
                .to(exchange)
                .with("investment.created");
    }

    @Bean
    Binding teamBinding(Queue teamQueue, TopicExchange exchange) {
        return BindingBuilder.bind(teamQueue)
                .to(exchange)
                .with("team.invite");
    }
}