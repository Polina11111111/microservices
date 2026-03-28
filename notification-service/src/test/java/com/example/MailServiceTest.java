package com.example;

import com.example.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MailServiceTest {

    @Test
    void shouldSendMail() {
        JavaMailSender sender = mock(JavaMailSender.class);
        MailService service = new MailService(sender);

        service.sendMail("test@test.com", "Test", "Hello");

        verify(sender).send(any(SimpleMailMessage.class));
    }
}