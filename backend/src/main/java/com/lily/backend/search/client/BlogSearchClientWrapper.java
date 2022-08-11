package com.lily.backend.search.client;

import com.lily.backend.search.dto.BlogSearchResult;
import com.lily.backend.search.dto.SearchMeta;
import com.lily.backend.search.request.BlogSearchRequest;
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
