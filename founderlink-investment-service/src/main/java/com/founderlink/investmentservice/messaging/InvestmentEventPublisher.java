package com.founderlink.investmentservice.messaging;

import com.founderlink.investmentservice.entity.Investment;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InvestmentEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange:founderlink.exchange}")
    private String exchange;

    public void publishInvestmentCreated(Investment inv) {
        Map<String, Object> event = Map.of(
                "eventType", "INVESTMENT_CREATED",
                "startupId", inv.getStartupId(),
                "investorId", inv.getInvestorId(),
                "amount", inv.getAmount().toString()
        );

        rabbitTemplate.convertAndSend(exchange, "investment.created", event);
    }
}