package com.lily.blogservice.service;

import com.lily.blogservice.client.KakaoBlogSearchSource;
import com.lily.blogservice.client.NaverBlogSearchSource;
import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.BlogSearchResult;
import com.lily.blogservice.dto.SearchMeta;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;


@Service
public class BlogSearchClientWrapper {

  @Autowired
  private CircuitBreakerFactory circuitBreakerFactory;

  @Autowired
  private KakaoBlogSearchSource kakaoBlogSearchSource;

  @Autowired
  private NaverBlogSearchSource naverBlogSearchSource;

  @Autowired
  private BlogSearchClient blogSearchClient;

  public BlogSearchResult search(final BlogSearchRequest request) {

    CircuitBreaker circuitBreaker = circuitBreakerFactory.create("searchClientCircuitBreaker");
    CircuitBreaker lastCircuitBreaker = circuitBreakerFactory.create("last");

    return circuitBreaker.run(() -> blogSearchClient.search(kakaoBlogSearchSource, request),
        throwable -> lastCircuitBreaker.run(() -> blogSearchClient.search(naverBlogSearchSource, request),
            finalThrowable -> emptyResult()));
  }

  private BlogSearchResult emptyResult() {
    SearchMeta meta = new SearchMeta();
    meta.setIsEndPage(true);
    meta.setPageableCount(0);
    meta.setTotalCount(0);
    return new BlogSearchResult(meta, new ArrayList<>());
  }

}
