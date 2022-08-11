package com.lily.backend.user;

import com.lily.backend.common.dto.APIResponse;
import com.lily.backend.user.dto.UserDto;
import com.lily.backend.user.request.LoginRequest;
import com.lily.backend.user.request.SignupRequest;
import com.lily.backend.user.response.JwtResponse;
import com.lily.backend.user.response.SingupResult;
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
  public APIResponse<SingupResult> register(@RequestBody final SignupRequest request) {

    UserDto newUser = userService.register(request);

    SingupResult result = SingupResult.success(newUser);
    return APIResponse.success(result);
  }

  @PostMapping("/login")
  public APIResponse<JwtResponse> signIn(@RequestBody final LoginRequest loginRequest) {

    JwtResponse jwtResponse = userService.authenticate(loginRequest);

    return APIResponse.success(jwtResponse);
  }
}
