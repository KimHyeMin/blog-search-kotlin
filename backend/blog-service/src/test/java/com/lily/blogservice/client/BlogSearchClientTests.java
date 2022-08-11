package com.lily.blogservice.client;

import static com.lily.blogservice.SearchDummyData.*;
import static com.lily.blogservice.client.ClientDummyData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.BlogSearchResult;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
public class BlogSearchClientTests {

  @Autowired
  private BlogSearchClient dut;

  @MockBean
  private RestTemplate restTemplate;


  public void setOnlyKakaoResponse() {
    ResponseEntity<Object> kakaoTestResponse =
        new ResponseEntity<>(testKakaoBlogSearchResultJson(), HttpStatus.OK);

    doReturn(kakaoTestResponse)
        .when(restTemplate)
        .exchange(
            matches(".*kakao.*"),
            any(),
            any(),
            (Class<Object>) any(),
            (Map<String, ?>) any());
  }

  public void setOnlyNaverResponse() {
    ResponseEntity<Object> naverTestResponse =
        new ResponseEntity<>(testNaverBlogSearchResultJson(), HttpStatus.OK);

    doReturn(naverTestResponse)
        .when(restTemplate)
        .exchange(
            matches(".*naver.*"),
            any(),
            any(),
            (Class<Object>) any(),
            (Map<String, ?>) any());
  }

  @Test
  public void mustGetValidKakaoBlogDocument() {
    setOnlyKakaoResponse();
    setOnlyNaverResponse();

    final BlogSearchRequest request = testBlogSearchRequest();
    final BlogSearchResult result = dut.search(request);
    assertEquals(result.getMetaData().getTotalCount(), KAKAO_TOTAL_COUNT);
  }

  @Test
  public void mustGetValidNaverBlogDocument() {
    setOnlyNaverResponse();
    final BlogSearchRequest request = testBlogSearchRequest();
    final BlogSearchResult result = dut.search(request);
    assertEquals(result.getMetaData().getTotalCount(), NAVER_TOTAL_COUNT);
  }

  @Test
  public void mustGetEmptyBlogDocument() {
    final BlogSearchRequest request = testBlogSearchRequest();
    final BlogSearchResult result = dut.search(request);
    assertEquals(result, BlogSearchResult.emptyResult());
  }

}
