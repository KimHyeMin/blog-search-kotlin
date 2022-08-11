package com.lily.blogservice.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class SearchMeta {

  private Integer totalCount;

  private Integer pageableCount;

  private Boolean isEndPage;

}
