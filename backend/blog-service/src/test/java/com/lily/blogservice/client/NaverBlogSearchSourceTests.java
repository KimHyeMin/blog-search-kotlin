package com.lily.blogservice.client;

import static com.lily.blogservice.SearchDummyData.testBlogSearchRequest;
import static com.lily.blogservice.client.ClientDummyData.*;
import static org.junit.jupiter.api.Assertions.*;

import com.lily.blogservice.dto.BlogDocument;
import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.SearchMeta;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class NaverBlogSearchSourceTests {

  private final NaverBlogSearchSource dut = testNaverBlogSearchSource();

  @Test
  public void mustGetValidHttpHeaders() {
    HttpHeaders headers = dut.getHttpHeaders();

    assertEquals(headers.get("Accept").get(0), MediaType.APPLICATION_JSON_VALUE);
    assertEquals(headers.get("X-Naver-Client-Id").get(0), dut.getClientId());
    assertEquals(headers.get("X-Naver-Client-Secret").get(0), dut.getClientSecret());
  }

  @Test
  public void mustGetValidParameters() {
    final BlogSearchRequest request = testBlogSearchRequest();
    final Map<String, Object> parameters = dut.getParameters(request);

    assertEquals(parameters.get("query"), request.getKeywords());
    assertEquals(parameters.get("sort"), dut.sortToQueryParam(request.getSort()));
    assertEquals(parameters.get("start"), request.getPage());
    assertEquals(parameters.get("display"), request.getSize());
  }

  @Test
  public void mustGetValidSearchMeta() {
    final Map<String, Object> naverBlogSearchResult = testNaverBlogSearchResult();
    final SearchMeta meta = dut.parseSearchMeta(naverBlogSearchResult);

    assertNotNull(meta);
    assertNotNull(meta.getTotalCount());
    assertNotNull(meta.getPageableCount());
    assertNotNull(meta.getIsEndPage());
  }

  @Test
  public void mustThrowWhenSearchMetaNotExist() {
    final Map<String, Object> naverBlogSearchResult = testNaverBlogSearchResult();
    naverBlogSearchResult.remove("total");

    Exception exception = assertThrows(NullPointerException.class, () -> {
      final SearchMeta meta = dut.parseSearchMeta(naverBlogSearchResult);
    });

    assertEquals(exception.getClass(), NullPointerException.class);
  }

  @Test
  public void mustGetValidBlogDocuments() {
    final Map<String, Object> naverBlogSearchResult = testNaverBlogSearchResult();
    final List<BlogDocument> blogDocuments = dut.parseBlogDocuments(naverBlogSearchResult);

    assertFalse(blogDocuments.isEmpty());
    blogDocuments.forEach(blogDocument -> {
      assertNull(blogDocument.getFavoriteId());
      assertNotNull(blogDocument.getBlogName());
      assertNotNull(blogDocument.getTitle());
      assertNotNull(blogDocument.getContents());
      assertNotNull(blogDocument.getUrl());
      assertNotNull(blogDocument.getThumbnail());
      assertNotNull(blogDocument.getDatetime());
    });
  }

  @Test
  public void mustGetValidEmptyDocuments() {
    final Map<String, Object> naverBlogSearchResult = testNaverBlogSearchResult();
    naverBlogSearchResult.put("items", Collections.emptyList());

    assertDoesNotThrow(() -> {
      final List<BlogDocument> blogDocuments = dut.parseBlogDocuments(naverBlogSearchResult);
      assertTrue(blogDocuments.isEmpty());
    });
  }

  @Test
  public void mustThrowWhenDocumentsNotExist() {
    final Map<String, Object> naverBlogSearchResult = testNaverBlogSearchResult();
    naverBlogSearchResult.remove("items");

    Exception exception = assertThrows(NullPointerException.class, () -> {
      final List<BlogDocument> blogDocuments = dut.parseBlogDocuments(naverBlogSearchResult);
    });

    assertEquals(exception.getClass(), NullPointerException.class);
  }

}
