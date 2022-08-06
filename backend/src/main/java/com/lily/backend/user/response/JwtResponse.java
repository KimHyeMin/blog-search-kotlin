package com.lily.backend.user.response;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class JwtResponse {

  private String token;
  private Long userId;
  private String name;
  private String email;

}
