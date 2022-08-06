package com.lily.backend.search.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MetaData {

  @JsonAlias("total_count")
  private Integer totalCount;

  @JsonAlias("pageable_count")
  private Integer pageableCount;

  @JsonAlias("is_end")
  private Boolean isEndPage;

}
