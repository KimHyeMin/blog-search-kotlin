package com.lily.backend.user;

import com.lily.backend.common.dto.APIResponse;
import com.lily.backend.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public APIResponse<SingupResult> register(@RequestBody SignupRequest request) {

    UserDto newUser = userService.register(request);

    SingupResult result = SingupResult.success(newUser);
    return APIResponse.<SingupResult>builder()
        .code(HttpStatus.OK.value())
        .result(result)
        .build();
  }
}
