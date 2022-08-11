package com.lily.userauth.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SignupRequest {

  private String firstName;
  private String lastName;
  private String email;
  private String password;

}
