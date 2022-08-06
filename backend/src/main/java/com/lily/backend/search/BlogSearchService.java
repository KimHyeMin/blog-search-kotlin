package com.lily.backend.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lily.backend.search.client.KakaoBlogSearchClient;
import com.lily.backend.search.request.BlogSearchRequest;
import com.lily.backend.search.response.KakaoBlogSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogSearchService {

  @Autowired
  private KakaoBlogSearchClient kakaoBlogSearchClient;

  public KakaoBlogSearchResponse searchBlogs(final BlogSearchRequest request)
      throws JsonProcessingException {

    //todo implement naver blog search with abstracting
    return kakaoBlogSearchClient.search(request);
  }

}
