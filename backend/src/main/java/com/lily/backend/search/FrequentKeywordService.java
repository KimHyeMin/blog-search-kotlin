package com.lily.backend.search;

import com.lily.backend.search.dto.FrequentKeyword;
import com.lily.backend.search.keyword.KeywordService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FrequentKeywordService {

  private static final int TOP_10 = 10;

  @Autowired
  private KeywordService keywordService;

  public List<FrequentKeyword> getList() {
    return keywordService.getTopKeywords(TOP_10);
  }

  public void marking(String keywords) {
      keywordService.increaseCount(keywords);
  }

}
