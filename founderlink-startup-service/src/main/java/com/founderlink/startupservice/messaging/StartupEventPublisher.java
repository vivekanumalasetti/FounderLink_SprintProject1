package com.founderlink.startupservice.messaging;

import com.founderlink.startupservice.entity.Startup;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StartupEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange:founderlink.exchange}")
    private String exchange;

    public void publishStartupCreated(Startup startup) {

        Map<String, Object> event = Map.of(
                "eventType", "STARTUP_CREATED",
                "startupId", startup.getId(),
                "founderId", startup.getFounderId(),
                "industry", startup.getIndustry(),
                "fundingGoal", startup.getFundingGoal()
        );

        rabbitTemplate.convertAndSend(exchange, "startup.created", event);
    }
}