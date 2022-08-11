package com.lily.backend.common.dto;

import com.lily.backend.blog.response.SimpleFavoriteResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@Builder
public class APIResponse<T> {
  private int code;
  private String errorMessage;
  private T result;

  public static <T> APIResponse<T> success(T data) {
    return APIResponse.<T>builder()
        .code(HttpStatus.OK.value())
        .result(data)
        .build();
  }
}
