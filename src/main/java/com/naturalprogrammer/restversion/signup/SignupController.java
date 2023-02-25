package com.naturalprogrammer.restversion.signup;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    public record SignupRequestDto(String email, String password) {}
    public record UserResourceDto(String email) {}

    @PostMapping(
            value = "/users",
            consumes = "application/vnd.com.example.signup-request+json; version=1.0",
            produces = "application/vnd.com.example.user+json; version=1.0"
    )
    public UserResourceDto signup(@RequestBody SignupRequestDto requestDto) {
        return new UserResourceDto(requestDto.email());
    }
}
