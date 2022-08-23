package com.lily.userauth.endpoint;

import com.lily.userauth.dto.APIResponse;
import com.lily.userauth.dto.JwtResponse;
import com.lily.userauth.dto.LoginRequest;
import com.lily.userauth.dto.SignupRequest;
import com.lily.userauth.dto.SingupResult;
import com.lily.userauth.dto.UserDto;
import com.lily.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public APIResponse<SingupResult> register(@RequestBody @Valid final SignupRequest request) {

    UserDto newUser = userService.register(request);

    SingupResult result = SingupResult.success(newUser);
    return APIResponse.success(result);
  }

  @PostMapping("/login")
  public APIResponse<JwtResponse> signIn(@RequestBody @Valid final LoginRequest loginRequest) {

    JwtResponse jwtResponse = userService.authenticate(loginRequest);

    return APIResponse.success(jwtResponse);
  }
}
