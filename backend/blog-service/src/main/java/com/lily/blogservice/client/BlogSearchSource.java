package com.lily.blogservice.client;

import com.lily.blogservice.dto.BlogDocument;
import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.SearchMeta;
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
