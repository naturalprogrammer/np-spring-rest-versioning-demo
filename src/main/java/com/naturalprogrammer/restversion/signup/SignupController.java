package com.naturalprogrammer.restversion.signup;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class SignupController {

    @Deprecated
    public record SignupRequestDto(String email, String password) {
        public static final String MEDIA_TYPE = "application/vnd.com.example.signup-request.v1+json";
    }

    public record SignupRequestDtoV2(String mobileNumber, String password) {
        public static final String MEDIA_TYPE = "application/vnd.com.example.signup-request.v2+json";
    }

    public record UserResourceDto(String email, String mobileNumber) {
        public static final String MEDIA_TYPE = "application/vnd.com.example.user.v1+json";
    }

    @Deprecated
    @PostMapping(
            consumes = SignupRequestDto.MEDIA_TYPE,
            produces = UserResourceDto.MEDIA_TYPE
    )
    public UserResourceDto signup(@RequestBody SignupRequestDto requestDto) {
        return new UserResourceDto(requestDto.email(), null);
    }

    @PostMapping(
            consumes = SignupRequestDtoV2.MEDIA_TYPE,
            produces = UserResourceDto.MEDIA_TYPE
    )
    public UserResourceDto signupWithMobile(@RequestBody SignupRequestDtoV2 requestDto) {
        return new UserResourceDto(requestDto.mobileNumber() + "@example.com", requestDto.mobileNumber());
    }
}
