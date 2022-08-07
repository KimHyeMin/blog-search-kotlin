package com.lily.backend.search.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lily.backend.search.dto.BlogDocument;
import com.lily.backend.search.dto.MetaData;
import com.lily.backend.search.request.BlogSearchRequest;
import com.lily.backend.search.dto.BlogSearchResult;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class BlogSearchClient {

  @Autowired
  private RestTemplate restTemplate;

  static private final ObjectMapper mapper = new ObjectMapper();

  public BlogSearchResult search(final BlogSearchSource source, final BlogSearchRequest request)
      throws Exception {
    HttpHeaders headers = source.getHttpHeaders(request);
    UriComponentsBuilder builder = source.getUriComponentsBuilder(request);
    HttpEntity<?> entity = new HttpEntity<>(headers);

    HttpEntity<String> response = restTemplate.exchange(
        builder.toUriString(),
        HttpMethod.GET,
        entity,
        String.class);

    final String jsonString = response.getBody();
    Map<String, Object> json = mapper.readValue(jsonString, Map.class);

    final MetaData meta = source.parseSearchMeta(json);
    final List<BlogDocument> documents = source.parseBlogDocuments(json);

    return new BlogSearchResult(meta, documents);
  }

}