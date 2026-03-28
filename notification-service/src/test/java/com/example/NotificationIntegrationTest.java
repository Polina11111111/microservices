package com.example;

import com.example.service.MailService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class NotificationIntegrationTest {

    private static GreenMail greenMail;

    @BeforeAll
    static void startMailServer() {
        greenMail = new GreenMail(ServerSetupTest.SMTP.dynamicPort());
        greenMail.setUser("test", "test");
        greenMail.start();
    }

    @AfterAll
    static void stopMailServer() {
        if (greenMail != null) {
            greenMail.stop();
        }
    }

    @Autowired
    private MailService mailService;

    @DynamicPropertySource
    static void configureMailProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.mail.host", () -> "localhost");
        registry.add("spring.mail.port", () -> greenMail.getSmtp().getPort());
        registry.add("spring.mail.username", () -> "test");
        registry.add("spring.mail.password", () -> "test");
    }

    @Test
    void shouldSendEmailAndReceiveIt() throws Exception {
        String email = "customer@example.com";
        String subject = "Welcome!";
        String body = "Your account is ready.";

        mailService.sendMail(email, subject, body);
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertEquals(1, receivedMessages.length, "Письмо должно быть получено сервером");
        assertEquals(subject, receivedMessages[0].getSubject());
        assertEquals(email, receivedMessages[0].getAllRecipients()[0].toString());
        assertEquals(body, receivedMessages[0].getContent().toString().trim());
    }
}