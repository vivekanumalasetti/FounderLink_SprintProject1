package com.founderlink.teamservice.messaging;

import com.founderlink.teamservice.entity.TeamMember;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TeamEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange:founderlink.exchange}")
    private String exchange;

    public void publishTeamInvite(TeamMember member) {

        Map<String, Object> event = Map.of(
                "eventType", "TEAM_INVITE_SENT",
                "startupId", member.getStartupId(),
                "invitedUserId", member.getUserId(),
                "role", member.getRole()
        );

        rabbitTemplate.convertAndSend(exchange, "team.invite", event);
    }
}