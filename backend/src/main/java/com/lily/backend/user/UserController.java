package com.lily.backend.user;

import com.lily.backend.common.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @PostMapping("/sign-up")
  public APIResponse<SingupResult> register(@RequestBody SingupRequest request) {

    SingupResult result = SingupResult.success("회원가입에 성공했습니다.");
    return APIResponse.<SingupResult>builder()
        .code(HttpStatus.OK.value())
        .result(result)
        .build();
  }
}
