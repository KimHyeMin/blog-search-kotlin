package com.lily.userauth.dto;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class JwtResponse {

  private String token;
  private UserDto user;

}
