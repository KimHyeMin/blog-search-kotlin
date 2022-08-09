package com.lily.backend.blog.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BlogDto {

  private Long favoriteId;
  private String title;
  private String contents;
  private String thumbnail;
  private String url;
  private String blogName;
  private LocalDateTime datetime;

  public boolean getLike() {
    return favoriteId != null;
  }
}
