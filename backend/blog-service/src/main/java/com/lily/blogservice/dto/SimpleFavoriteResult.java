package com.lily.blogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleFavoriteResult {

  private boolean success;
  private Long favoriteId;

  public static SimpleFavoriteResult fail() {
    return new SimpleFavoriteResult(false, null);
  }


  public static SimpleFavoriteResult with(Long favoriteId) {
    return new SimpleFavoriteResult(true, favoriteId);
  }
}
