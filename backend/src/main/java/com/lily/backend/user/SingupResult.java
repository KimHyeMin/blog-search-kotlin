package com.lily.backend.user;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SingupResult {

  private String message;

  public static SingupResult success(String message) {
    SingupResult singupResult = new SingupResult();
    singupResult.setMessage(message);
    return singupResult;
  }
}
