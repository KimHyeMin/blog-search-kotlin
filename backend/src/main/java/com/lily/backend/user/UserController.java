package com.lily.backend.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/sign-up")
    public SingupResult register(@RequestBody SingupRequest request) {
        return SingupResult.success("회원가입에 성공했습니다.");
    }
}
