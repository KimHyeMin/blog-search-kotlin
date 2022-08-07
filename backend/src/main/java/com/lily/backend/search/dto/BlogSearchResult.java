package com.lily.backend.search.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class BlogSearchResult {

  private SearchMeta metaData;

  private List<BlogDocument> blogList;

}
