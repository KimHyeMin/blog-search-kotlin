package com.lily.blogservice.dto;


import com.lily.trends.dto.FrequentKeyword;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FrequentResult {

  private List<FrequentKeyword> list;

}
