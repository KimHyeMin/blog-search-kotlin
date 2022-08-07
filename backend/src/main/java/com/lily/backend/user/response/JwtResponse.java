package com.lily.backend.user.response;


import com.lily.backend.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class JwtResponse {

  private String token;
  private UserDto user;

}
