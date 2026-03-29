package com.founderlink.notificationservice.messaging;

import com.founderlink.notificationservice.entity.Notification;
import com.founderlink.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationRepository repo;

    @RabbitListener(queues = "${rabbitmq.queue.startup:startup.queue}")
    public void onStartupCreated(Map<String, Object> event) {

        Notification n = new Notification();
        n.setType("STARTUP_CREATED");
        n.setMessage("New startup in " + event.get("industry"));
        repo.save(n);
    }

    @RabbitListener(queues = "${rabbitmq.queue.investment:investment.queue}")
    public void onInvestmentCreated(Map<String, Object> event) {

        Notification n = new Notification();
        n.setType("INVESTMENT_CREATED");
        n.setMessage("Investment: " + event.get("amount"));
        repo.save(n);
    }

    @RabbitListener(queues = "${rabbitmq.queue.team:team.queue}")
    public void onTeamInvite(Map<String, Object> event) {

        Notification n = new Notification();
        n.setUserId(Long.parseLong(event.get("invitedUserId").toString()));
        n.setType("TEAM_INVITE_SENT");
        n.setMessage("Team invite for startup " + event.get("startupId"));
        repo.save(n);
    }
}