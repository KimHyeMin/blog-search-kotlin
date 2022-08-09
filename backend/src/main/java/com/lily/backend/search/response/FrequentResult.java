package com.lily.backend.search.response;

import com.lily.backend.search.dto.FrequentKeyword;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FrequentResult {

  private List<FrequentKeyword> list;

}
