package com.lily.userauth.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SingupResult {

  private String message;
  private UserDto user;

  public static SingupResult success(UserDto userDto) {
    SingupResult singupResult = new SingupResult();
    singupResult.setUser(userDto);
    return singupResult;
  }
}
