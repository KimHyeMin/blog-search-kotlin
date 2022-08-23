package com.lily.trends;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lily.trends.dto.FrequentKeyword;
import java.util.List;
import org.junit.jupiter.api.Test;


public class LocalKeywordServiceTests {

  @Test
  public void mustGetEmptyTopKeywords() {
    LocalKeywordService localKeywordService = new LocalKeywordService();
    assertNotNull(localKeywordService.getTopKeywords(5));
    assertEquals(localKeywordService.getTopKeywords(5).size(), 0);
  }

  LocalKeywordService testIndexedLocalKeywordService() {
    LocalKeywordService localKeywordService = new LocalKeywordService();

    for (char ch = 'A'; ch <= 'Z'; ++ch) {
      final String keywords = String.valueOf(ch);

      for (int i = 0; i < ch - 'A'; ++i) {
        localKeywordService.increaseCount(keywords);
      }
    }
    localKeywordService.refreshMap();

    return localKeywordService;
  }

  @Test
  public void mustGetValidTop5Keywords() {
    final LocalKeywordService localKeywordService = testIndexedLocalKeywordService();

    List<FrequentKeyword> topKeywords = localKeywordService.getTopKeywords(5);
    assertEquals(topKeywords.size(), 5);

    assertEquals(topKeywords.get(0), new FrequentKeyword("Z", 25));
    assertEquals(topKeywords.get(1), new FrequentKeyword("Y", 24));
    assertEquals(topKeywords.get(2), new FrequentKeyword("X", 23));
    assertEquals(topKeywords.get(3), new FrequentKeyword("W", 22));
    assertEquals(topKeywords.get(4), new FrequentKeyword("V", 21));
  }

  @Test
  public void mustThrowsWhenTopNumInvalid() {
    final LocalKeywordService localKeywordService = testIndexedLocalKeywordService();

    Exception exception = assertThrows(RuntimeException.class, () -> {
      localKeywordService.getTopKeywords(-1);
    });

    assertEquals(exception.getClass(), RuntimeException.class);
  }

}
