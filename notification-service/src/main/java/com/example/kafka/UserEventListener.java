package com.example.kafka;

import com.example.event.EventType;
import com.example.event.UserEvent;
import com.example.service.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private final MailService mailService;

    public UserEventListener(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(UserEvent event) {
        if (event.getEventType() == EventType.USER_CREATED) {
            mailService.sendMail(
                    event.getEmail(),
                    "Аккаунт создан",
                    "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан."
            );
        }

        if (event.getEventType() == EventType.USER_DELETED) {
            mailService.sendMail(
                    event.getEmail(),
                    "Аккаунт удалён",
                    "Здравствуйте! Ваш аккаунт был удалён."
            );
        }
    }
}