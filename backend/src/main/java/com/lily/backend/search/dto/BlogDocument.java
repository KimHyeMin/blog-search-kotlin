package com.lily.backend.search.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BlogDocument {

  @JsonAlias("blogname")
  private String blogName;

  private String title;

  private String contents;

  private String url;

  private String thumbnail;

  private LocalDateTime datetime;

}
