package com.lily.blogservice.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class BlogSearchResult {

  private SearchMeta metaData;

  private List<BlogDocument> blogList;

  public static BlogSearchResult emptyResult() {
    SearchMeta meta = new SearchMeta();
    meta.setIsEndPage(true);
    meta.setPageableCount(0);
    meta.setTotalCount(0);
    return new BlogSearchResult(meta, new ArrayList<>());
  }

}