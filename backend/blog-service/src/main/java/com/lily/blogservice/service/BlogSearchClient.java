package com.lily.blogservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lily.blogservice.client.BlogSearchSource;
import com.lily.blogservice.dto.BlogDocument;
import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.BlogSearchResult;
import com.lily.blogservice.dto.SearchMeta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
public class BlogSearchClient {

  @Autowired
  private RestTemplate restTemplate;

  static private ObjectMapper mapper = new ObjectMapper();

  public BlogSearchResult search(final BlogSearchSource source, final BlogSearchRequest request) {
    final String urlTemplate = source.getUrlTemplate();
    final HttpEntity<?> entity = new HttpEntity<>(source.getHttpHeaders(request));
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
      json = mapper.readValue(jsonString, Map.class);
    } catch (JsonProcessingException e) {
      log.error("[Todo check] json mapping failed", e);
    }

    final SearchMeta meta = source.parseSearchMeta(json);
    final List<BlogDocument> documents = source.parseBlogDocuments(json);

    return new BlogSearchResult(meta, documents);
  }

}
