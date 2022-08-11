package com.lily.trends.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FrequentKeyword {

  private String keyword;
  private Integer count;

}
