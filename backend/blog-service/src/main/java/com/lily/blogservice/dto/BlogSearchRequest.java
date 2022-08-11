package com.lily.blogservice.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Getter
@Setter
public class BlogSearchRequest {

  private String keywords;

  private SearchSort sort = SearchSort.ACCURACY;

  @Min(1) @Max(1000)
  private int page = 1;

  @Min(0) @Max(500)
  private int size = 30;

  private boolean first = false; // Marking the ranking of keyword only when 'first' is true

}
