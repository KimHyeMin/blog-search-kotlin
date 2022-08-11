package com.lily.userauth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SignupRequest {

  @NotBlank(message = "Please input your first name")
  private String firstName;

  @NotBlank(message = "Please input your last name")
  private String lastName;

  @NotBlank(message = "Please input your email")
  @Email(message = "Please follow the email format")
  private String email;

  @NotBlank(message = "Please input your password")
  @Size(min = 5, message = "The password must be at least 5 .")
  private String password;

}
