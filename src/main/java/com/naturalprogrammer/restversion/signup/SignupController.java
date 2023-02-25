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
            consumes = "application/json",
            produces = "application/json"
    )
    public UserResourceDto signup(@RequestBody SignupRequestDto requestDto) {
        return new UserResourceDto(requestDto.email());
    }
}
