package com.lily.backend.search.client;

import com.lily.backend.search.dto.BlogDocument;
import com.lily.backend.search.dto.MetaData;
import com.lily.backend.search.request.BlogSearchRequest;
import com.lily.backend.search.request.SearchSort;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class KakaoBlogSearchSource implements BlogSearchSource {

  @Value("${client.kakao.token}")
  private String clientToken;

  @Value("${client.kakao.url}")
  private String kakaoBlogSearchUrl;

  @Override
  public HttpHeaders getHttpHeaders(BlogSearchRequest request) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    headers.set("Authorization", clientToken);
    return headers;
  }

  @Override
  public UriComponentsBuilder getUriComponentsBuilder(BlogSearchRequest request) {
    return UriComponentsBuilder
        .fromHttpUrl(kakaoBlogSearchUrl)
        .queryParam("query", request.getKeywords())
        .queryParam("sort", sortToQueryParam(request.getSort()))
        .queryParam("page", request.getPage())
        .queryParam("size", request.getSize());
  }

  @Override
  public MetaData parseSearchMeta(Map<String, Object> json) {
    Map<String, Object> metaJson = ((Map<String, Object>) json.get("meta"));

    MetaData searchMeta = new MetaData();
    searchMeta.setTotalCount((int) metaJson.get("total_count"));
    searchMeta.setPageableCount((int) metaJson.get("pageable_count"));
    searchMeta.setIsEndPage((boolean) metaJson.get("is_end"));
    return searchMeta;
  }

  @Override
  public List<BlogDocument> parseBlogDocuments(Map<String, Object> json) {
    return ((List<Map<String, String>>) json.get("documents"))
        .stream()
        .map(docJson -> parseBlogDocument(docJson))
        .toList();
  }

  private String sortToQueryParam(final SearchSort sort) {
    return sort.name().toLowerCase();
  }

  private BlogDocument parseBlogDocument(final Map<String, String> docJson) {
    BlogDocument doc = new BlogDocument();

    doc.setTitle(docJson.getOrDefault("title", ""));
    doc.setContents(docJson.getOrDefault("contents", ""));
    doc.setUrl(docJson.getOrDefault("url", ""));
    doc.setThumbnail(docJson.getOrDefault("thumbnail", ""));
    doc.setBlogName(docJson.getOrDefault("blogname", ""));

    final String postDate = docJson.getOrDefault("datetime", "");
    if (!postDate.isEmpty()) {
      doc.setDatetime(LocalDateTime.parse(postDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

    return doc;
  }

}
