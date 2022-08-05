package com.lily.backend.user;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SignupRequest {

  private String firstName;
  private String secondName;
  private String email;
  private String password;

}
