package com.example;

import com.example.controller.MailController;
import com.example.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MailController.class)
class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailService mailService;

    @Test
    void shouldSendMail() throws Exception {
        mockMvc.perform(post("/api/mail")
                        .param("email", "test@test.com")
                        .param("subject", "Test")
                        .param("text", "Hello"))
                .andExpect(status().isOk());
    }
}