package com.lily.backend.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BlogSearchRequest {

  String keywords;

  ResultSort sort;

  int page;

  int size;

}
