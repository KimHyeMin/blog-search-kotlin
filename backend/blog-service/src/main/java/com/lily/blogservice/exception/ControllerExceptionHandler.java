package com.lily.blogservice.exception;

import com.lily.blogservice.dto.APIResponse;
import com.lily.userauth.exception.SignupFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler({BindException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public APIResponse invalidParameterException(final BindException ex) {
    log.warn("Bad Request: " + ex.getMessage());
    FieldError error = ex.getBindingResult().getFieldError();
    String message = String.format("%s %s", error.getField(), error.getDefaultMessage());
    return APIResponse.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .errorMessage(message)
            .build();
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public APIResponse authenticationException(final AuthenticationException e) {
    log.warn("Login error: " + e.getMessage());
    return APIResponse.builder()
        .code(HttpStatus.UNAUTHORIZED.value())
        .errorMessage("로그인에 실패했습니다.")
        .build();
  }

  @ExceptionHandler({SignupFailedException.class, NoResourceFindException.class})
  @ResponseStatus(HttpStatus.OK)
  public APIResponse businessException(final Exception e) {
    log.warn("business error: " + e.getMessage());
    return APIResponse.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .errorMessage(e.getMessage())
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public APIResponse serverException(final Exception e) {
    log.warn("Server error: " + e.getMessage());
    return APIResponse.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .errorMessage("There is system error.")
        .build();
  }

}
