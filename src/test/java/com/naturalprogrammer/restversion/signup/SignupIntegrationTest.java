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
    @Deprecated
    void should_signup_via_email() throws Exception {

        var email = "mail@example.com";

        mvc.perform(post("/users")
                        .contentType("application/vnd.com.example.signup-request.v1+json")
                        .content("""
                                   {
                                        "email" : "%s",
                                        "password" : "secret"
                                   }     
                                """.formatted(email)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/vnd.com.example.user.v1+json"))
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("mobileNumber").doesNotExist());
    }

    @Test
    void should_signup_via_mobile() throws Exception {

        var mobileNumber = "9999999999";

        mvc.perform(post("/users")
                        .contentType("application/vnd.com.example.signup-request.v2+json")
                        .content("""
                                   {
                                        "mobileNumber" : "%s",
                                        "password" : "secret"
                                   }     
                                """.formatted(mobileNumber)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/vnd.com.example.user.v1+json"))
                .andExpect(jsonPath("email").value(mobileNumber + "@example.com"))
                .andExpect(jsonPath("mobileNumber").value(mobileNumber));
    }
}
