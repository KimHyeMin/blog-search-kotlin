package com.lily.backend.search.client;

import com.lily.backend.search.dto.BlogDocument;
import com.lily.backend.search.dto.MetaData;
import com.lily.backend.search.request.BlogSearchRequest;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;


public interface BlogSearchSource {

  HttpHeaders getHttpHeaders(final BlogSearchRequest request);

  UriComponentsBuilder getUriComponentsBuilder(final BlogSearchRequest request);

  MetaData parseSearchMeta(Map<String, Object> json);

  List<BlogDocument> parseBlogDocuments(Map<String, Object> json);

}
