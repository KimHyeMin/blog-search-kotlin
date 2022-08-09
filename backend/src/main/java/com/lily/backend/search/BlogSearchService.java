package com.lily.backend.search;

import com.lily.backend.search.client.BlogSearchClient;
import com.lily.backend.search.client.KakaoBlogSearchSource;
import com.lily.backend.search.client.NaverBlogSearchSource;
import com.lily.backend.search.dto.BlogSearchResult;
import com.lily.backend.search.request.BlogSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BlogSearchService {

  @Autowired
  private BlogSearchClient blogSearchClient;

  @Autowired
  private KakaoBlogSearchSource kakaoBlogSearchSource;

  @Autowired
  private NaverBlogSearchSource naverBlogSearchSource;

  @Autowired
  private FrequentKeywordService frequentKeywordService;

  public BlogSearchResult searchBlogs(final BlogSearchRequest request) {
    String errorMessage = "";
    frequentKeywordService.marking(request.getKeywords());

    try {
      return blogSearchClient.search(kakaoBlogSearchSource, request);
    } catch (Exception e) {
      log.error("Kakao Search Exception: ", e);
      errorMessage += "Kakao Search Exception: " + e.getMessage();
    }

    try {
      return blogSearchClient.search(naverBlogSearchSource, request);
    } catch (Exception e) {
      log.error("Naver Search Exception: ", e);
      errorMessage += ", Naver Search Exception: " + e.getMessage();
    }

    throw new RuntimeException(errorMessage);
  }

}
