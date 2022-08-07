package com.lily.backend.search.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MetaData {

  private Integer totalCount;

  private Integer pageableCount;

  private Boolean isEndPage;

}
