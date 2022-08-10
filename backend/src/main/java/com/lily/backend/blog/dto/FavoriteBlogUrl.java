package com.lily.backend.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
public class FavoriteBlogUrl {

  private Integer urlHashCode;
  private Long favoriteId;

}
