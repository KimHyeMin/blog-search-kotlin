package com.lily.backend.search.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lily.backend.search.dto.BlogDocument;
import com.lily.backend.search.dto.BlogSearchResult;
import com.lily.backend.search.dto.SearchMeta;
import com.lily.backend.search.request.BlogSearchRequest;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BlogSearchClient {

  @Autowired
  private RestTemplate restTemplate;

  static private ObjectMapper mapper = new ObjectMapper();

  public BlogSearchResult search(final BlogSearchSource source, final BlogSearchRequest request)
      throws Exception {
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
    final Map<String, Object> json = mapper.readValue(jsonString, Map.class);

    final SearchMeta meta = source.parseSearchMeta(json);
    final List<BlogDocument> documents = source.parseBlogDocuments(json);

    return new BlogSearchResult(meta, documents);
  }

}
