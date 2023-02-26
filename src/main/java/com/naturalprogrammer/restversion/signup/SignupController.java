package com.naturalprogrammer.restversion.signup;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    public record SignupRequestDto(String email, String password) {
        public static final String MEDIA_TYPE = "application/vnd.com.example.signup-request+json; version=1.0";
    }

    public record UserResourceDto(String email) {
        public static final String MEDIA_TYPE = "application/vnd.com.example.user+json; version=1.0";
    }

    @PostMapping(
            value = "/users",
            consumes = SignupRequestDto.MEDIA_TYPE,
            produces = UserResourceDto.MEDIA_TYPE
    )
    public UserResourceDto signup(@RequestBody SignupRequestDto requestDto) {
        return new UserResourceDto(requestDto.email());
    }
}
