package com.lily.userauth.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

  @NotEmpty(message = "Please input email")
  private String email;

  @NotEmpty(message = "Please input password")
  private String password;

}
