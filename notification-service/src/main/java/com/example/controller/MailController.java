
package com.example.controller;

import com.example.service.MailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    public String sendMail(
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String text
    ) {

        mailService.sendMail(email, subject, text);

        return "Mail sent";
    }
}