package com.lily.backend.search;

import com.lily.backend.search.client.BlogSearchClient;
import com.lily.backend.search.dto.BlogSearchRequest;
import com.lily.backend.search.dto.BlogSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogSearchService {

  @Autowired
  private BlogSearchClient blogSearchClient;

  public BlogSearchResult searchBlogs(final BlogSearchRequest request) {
    final String responseBody = blogSearchClient.search(request);
    //todo parse response data
    return BlogSearchResult
        .builder()
        .responseBody(responseBody)
        .build();
  }

}
