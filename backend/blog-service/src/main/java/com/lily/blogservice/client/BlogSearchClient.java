package com.lily.blogservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lily.blogservice.dto.BlogDocument;
import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.BlogSearchResult;
import com.lily.blogservice.dto.SearchMeta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@RequiredArgsConstructor
public class BlogSearchClient {

  static private final ObjectMapper MAPPER = new ObjectMapper();

  private final RestTemplate restTemplate;

  private final KakaoBlogSearchSource kakaoBlogSearchSource;

  private final NaverBlogSearchSource naverBlogSearchSource;

  private final CircuitBreakerFactory circuitBreakerFactory;


  private BlogSearchResult _search(final BlogSearchSource source, final BlogSearchRequest request) {
    final String urlTemplate = source.getUrlTemplate();
    final HttpEntity<?> entity = new HttpEntity<>(source.getHttpHeaders());
    final Map<String, Object> parameters = source.getParameters(request);

    final HttpEntity<String> response = restTemplate.exchange(
        urlTemplate,
        HttpMethod.GET,
        entity,
        String.class,
        parameters
    );

    final String jsonString = response.getBody();
    Map<String, Object> json = new HashMap<>();
    try {
      json = MAPPER.readValue(jsonString, Map.class);
    } catch (JsonProcessingException e) {
      log.error("[Todo check] json mapping failed", e);
    }

    final SearchMeta meta = source.parseSearchMeta(json);
    final List<BlogDocument> documents = source.parseBlogDocuments(json);

    return new BlogSearchResult(meta, documents);
  }

  public BlogSearchResult search(final BlogSearchRequest request) {
    CircuitBreaker circuitBreaker = circuitBreakerFactory.create("searchClientCircuitBreaker");
    CircuitBreaker lastCircuitBreaker = circuitBreakerFactory.create("last");

    return circuitBreaker.run(
        () -> _search(kakaoBlogSearchSource, request),
        throwable -> lastCircuitBreaker.run(
            () -> _search(naverBlogSearchSource, request),
            finalThrowable -> BlogSearchResult.emptyResult())
    );
  }

}
