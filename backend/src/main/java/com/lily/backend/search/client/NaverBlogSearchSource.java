package com.lily.backend.search.client;

import com.lily.backend.search.dto.BlogDocument;
import com.lily.backend.search.dto.SearchMeta;
import com.lily.backend.search.request.BlogSearchRequest;
import com.lily.backend.search.request.SearchSort;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class NaverBlogSearchSource implements BlogSearchSource {

  static int MAX_SEARCH_SIZE = 100;

  static int MAX_SEARCH_START = 1000;

  static DateTimeFormatter NAVER_BLOG_DATE_TIME = DateTimeFormatter.ofPattern("yyyyMMdd");

  @Value("${client.naver.client.id}")
  private String clientId;

  @Value("${client.naver.client.secret}")
  private String clientSecret;

  @Value("${client.naver.url}")
  private String naverBlogSearchUrl;


  @Override
  public String getUrlTemplate() {
    return UriComponentsBuilder.fromHttpUrl(naverBlogSearchUrl)
        .queryParam("query", "{query}")
        .queryParam("sort", "{sort}")
        .queryParam("start", "{start}")
        .queryParam("display", "{display}")
        .encode()
        .toUriString();
  }


  @Override
  public HttpHeaders getHttpHeaders(BlogSearchRequest request) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    headers.set("X-Naver-Client-Id", clientId);
    headers.set("X-Naver-Client-Secret", clientSecret);
    return headers;
  }

  String sortToQueryParam(final SearchSort sort) {
    return switch (sort) {
      case RECENCY -> "date";
      case ACCURACY -> "sim";
    };
  }

  @Override
  public Map<String, Object> getParameters(BlogSearchRequest request) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("query", request.getKeywords());
    parameters.put("sort", sortToQueryParam(request.getSort()));
    parameters.put("start", (request.getPage() - 1) * request.getSize() + 1);
    parameters.put("display", request.getSize());

    return parameters;
  }

  @Override
  public SearchMeta parseSearchMeta(Map<String, Object> json) {
    int total = (int) json.get("total");
    int start = (int) json.get("start");
    int display = (int) json.get("display");

    SearchMeta searchMeta = new SearchMeta();
    searchMeta.setTotalCount(total);
    searchMeta.setPageableCount(Math.min(total, MAX_SEARCH_START + MAX_SEARCH_SIZE));
    searchMeta.setIsEndPage(total <= start + display - 1);

    return searchMeta;
  }

  BlogDocument parseBlogDocument(final Map<String, String> docJson) {
    BlogDocument doc = new BlogDocument();

    doc.setTitle(docJson.getOrDefault("title", ""));
    doc.setContents(docJson.getOrDefault("description", ""));
    doc.setUrl(docJson.getOrDefault("link", ""));
    doc.setThumbnail("");
    doc.setBlogName(docJson.getOrDefault("bloggername", ""));

    final String postDate = docJson.getOrDefault("postdate", "");
    if (!postDate.isEmpty()) {
      doc.setDatetime(LocalDate.parse(postDate, NAVER_BLOG_DATE_TIME).atStartOfDay());
    }

    return doc;
  }

  @Override
  public List<BlogDocument> parseBlogDocuments(Map<String, Object> json) {
    return ((List<Map<String, String>>) json.get("items"))
        .stream()
        .map(docJson -> parseBlogDocument(docJson))
        .toList();
  }

}