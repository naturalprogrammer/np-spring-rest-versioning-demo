package com.naturalprogrammer.restversion.signup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SignupIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void should_signup() throws Exception {

        var email = "mail@example.com";

        mvc.perform(post("/users")
                        .contentType("application/vnd.com.example.signup-request+json; version=1.0")
                        .content("""
                                   {
                                        "email" : "%s",
                                        "password" : "secret"
                                   }     
                                """.formatted(email)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/vnd.com.example.user+json; version=1.0"))
                .andExpect(jsonPath("email").value(email));
    }
}
