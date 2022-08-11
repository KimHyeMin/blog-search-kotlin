package com.lily.trends;


import com.lily.trends.dto.FrequentKeyword;
import java.util.List;

public interface KeywordService {

  List<FrequentKeyword> getTopKeywords(final int top);

  void increaseCount(final String keywords);

}
