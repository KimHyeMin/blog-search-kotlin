package com.lily.backend.search.client;

import com.lily.backend.search.dto.BlogSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class KakaoBlogSearchClient implements BlogSearchClient {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${client.kakao.token}")
  private String clientToken;

  @Value("${client.kakao.url}")
  private String kakaoBlogSearchUrl;

  public String search(final BlogSearchRequest request) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    headers.set("Authorization", clientToken);

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(kakaoBlogSearchUrl)
        .queryParam("query", request.getKeywords())
        .queryParam("sort", request.getSort().getRequestParam())
        .queryParam("page", request.getPage())
        .queryParam("size", request.getSize());

    HttpEntity<?> entity = new HttpEntity<>(headers);

    HttpEntity<String> response = restTemplate.exchange(
        builder.toUriString(),
        HttpMethod.GET,
        entity,
        String.class);

    return response.getBody();
  }

}
