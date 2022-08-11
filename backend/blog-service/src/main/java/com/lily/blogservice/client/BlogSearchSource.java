package com.lily.blogservice.client;

import com.lily.blogservice.dto.BlogDocument;
import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.SearchMeta;
import com.lily.blogservice.dto.SearchSort;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;


public interface BlogSearchSource {

  String getUrlTemplate();

  HttpHeaders getHttpHeaders();

  String sortToQueryParam(final SearchSort sort);

  Map<String, Object> getParameters(final BlogSearchRequest request);

  SearchMeta parseSearchMeta(final Map<String, Object> json);

  List<BlogDocument> parseBlogDocuments(final Map<String, Object> json);

}
