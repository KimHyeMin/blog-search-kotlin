package com.lily.backend.search.keyword;

import com.lily.backend.search.dto.FrequentKeyword;
import java.util.List;

public interface KeywordService {

  List<FrequentKeyword> getTopKeywords(final int top);

  void increaseCount(final String keywords);

}
