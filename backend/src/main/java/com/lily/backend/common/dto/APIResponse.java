package com.lily.backend.common.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class APIResponse<T> {
  int code;
  String errorMessage;
  T result;
}
