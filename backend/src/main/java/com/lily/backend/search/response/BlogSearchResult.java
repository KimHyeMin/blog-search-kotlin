package com.lily.backend.search.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.lily.backend.search.dto.BlogDocument;
import com.lily.backend.search.dto.MetaData;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class BlogSearchResult {

  @JsonAlias("meta")
  private MetaData metaData;

  private List<BlogDocument> blogList;

}
