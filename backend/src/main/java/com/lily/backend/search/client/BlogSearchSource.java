package com.lily.backend.search.client;

import com.lily.backend.search.dto.BlogDocument;
import com.lily.backend.search.dto.SearchMeta;
import com.lily.backend.search.request.BlogSearchRequest;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;


public interface BlogSearchSource {

  String getUrlTemplate();

  HttpHeaders getHttpHeaders(final BlogSearchRequest request);

  Map<String, Object> getParameters(final BlogSearchRequest request);

  SearchMeta parseSearchMeta(Map<String, Object> json);

  List<BlogDocument> parseBlogDocuments(Map<String, Object> json);

}
