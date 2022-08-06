package com.lily.backend.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class APIResponse<T> {
  private int code;
  private String errorMessage;
  private T result;
}
