package com.lily.backend.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
public class UserDto {

  private String name;
  private String email;

}
