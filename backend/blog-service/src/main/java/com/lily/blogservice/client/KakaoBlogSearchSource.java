package com.lily.blogservice.client;

import com.lily.blogservice.dto.BlogDocument;
import com.lily.blogservice.dto.SearchMeta;
import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.SearchSort;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;


@Getter
@AllArgsConstructor
public class KakaoBlogSearchSource implements BlogSearchSource {

  private String clientToken;

  private String blogSearchUrl;

  @Override
  public String getUrlTemplate() {
    return UriComponentsBuilder.fromHttpUrl(blogSearchUrl)
        .queryParam("query", "{query}")
        .queryParam("sort", "{sort}")
        .queryParam("page", "{page}")
        .queryParam("size", "{size}")
        .encode()
        .toUriString();
  }

  @Override
  public HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    headers.set("Authorization", clientToken);
    return headers;
  }

  @Override
  public String sortToQueryParam(final SearchSort sort) {
    return sort.name().toLowerCase();
  }

  @Override
  public Map<String, Object> getParameters(final BlogSearchRequest request) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("query", request.getKeywords());
    parameters.put("sort", sortToQueryParam(request.getSort()));
    parameters.put("page", request.getPage());
    parameters.put("size", request.getSize());

    return parameters;
  }

  @Override
  public SearchMeta parseSearchMeta(final Map<String, Object> json) {
    Map<String, Object> metaJson = ((Map<String, Object>) json.get("meta"));

    SearchMeta searchMeta = new SearchMeta();
    searchMeta.setTotalCount((int) metaJson.get("total_count"));
    searchMeta.setPageableCount((int) metaJson.get("pageable_count"));
    searchMeta.setIsEndPage((boolean) metaJson.get("is_end"));

    return searchMeta;
  }

  BlogDocument parseBlogDocument(final Map<String, String> docJson) {
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

  @Override
  public List<BlogDocument> parseBlogDocuments(Map<String, Object> json) {
    return ((List<Map<String, String>>) json.get("documents"))
        .stream()
        .map(docJson -> parseBlogDocument(docJson))
        .collect(Collectors.toList());
  }

}
