package com.lily.backend.common.exception;

public class SignupFailedException extends RuntimeException {

  public SignupFailedException(String message) {
    super(message);
  }
}
