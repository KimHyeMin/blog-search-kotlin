package com.lily.blogservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientDummyData {
  public final static String KAKAO_TEST_TOKEN = "KAKAO_TEST_TOKEN";
  public final static String KAKAO_TEST_URL = "KAKAO_TEST_URL";

  public final static int KAKAO_TOTAL_COUNT = 147147;


  public final static String NAVER_TEST_CLIENT_ID = "NAVER_TEST_CLIENT_ID";
  public final static String NAVER_TEST_CLIENT_SECRET = "NAVER_TEST_CLIENT_SECRET";
  public final static String NAVER_TEST_URL = "NAVER_TEST_URL";
  public final static int NAVER_TOTAL_COUNT = 123123;

  public static KakaoBlogSearchSource testKakaoBlogSearchSource() {
    return new KakaoBlogSearchSource(KAKAO_TEST_TOKEN, KAKAO_TEST_URL);
  }


  static Map<String, String> testKakaoBlogDoc() {
    Map<String, String> result = new HashMap<>();

    final int testId = (int) (Math.random() * 10000);

    result.put("title", "title" + testId);
    result.put("contents", "contents" + testId);
    result.put("url", "https://testblog.com/" + testId);
    result.put("thumbnail", "https://testurl.com/" + testId);
    result.put("blogname", "blogname" + testId);
    result.put("datetime", "2022-05-30T10:15:30.000+09:00");

    return result;
  }

  public static Map<String, Object> testKakaoBlogSearchResult() {
    Map<String, Object> result = new HashMap<>();

    Map<String, Object> metaResult = new HashMap<>();
    metaResult.put("total_count", KAKAO_TOTAL_COUNT);
    metaResult.put("pageable_count", 500);
    metaResult.put("is_end", false);
    result.put("meta", metaResult);

    List<Map<String, String>> blogDocumentsResult = new ArrayList<>();
    for (int i = 0; i < 11; i++) {
      blogDocumentsResult.add(testKakaoBlogDoc());
    }
    result.put("documents", blogDocumentsResult);

    return result;
  }

  public static String testKakaoBlogSearchResultJson() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(testKakaoBlogSearchResult());
    } catch (JsonProcessingException e) {
      return "";
    }
  }

  public static NaverBlogSearchSource testNaverBlogSearchSource() {
    return new NaverBlogSearchSource(
        NAVER_TEST_CLIENT_ID, NAVER_TEST_CLIENT_SECRET, NAVER_TEST_URL);
  }

  static Map<String, String> testNaverBlogDoc() {
    Map<String, String> result = new HashMap<>();

    final int testId = (int) (Math.random() * 10000);

    result.put("title", "title" + testId);
    result.put("description", "description" + testId);
    result.put("link", "https://testblog.com/" + testId);
    result.put("thumbnail", "");
    result.put("bloggername", "bloggername" + testId);
    result.put("postdate", "20220530");

    return result;
  }

  public static Map<String, Object> testNaverBlogSearchResult() {
    Map<String, Object> result = new HashMap<>();

    result.put("total", NAVER_TOTAL_COUNT);
    result.put("start", 1);
    result.put("display", 30);

    List<Map<String, String>> blogDocumentsResult = new ArrayList<>();
    for (int i = 0; i < 11; i++) {
      blogDocumentsResult.add(testNaverBlogDoc());
    }
    result.put("items", blogDocumentsResult);

    return result;
  }

  public static String testNaverBlogSearchResultJson() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(testNaverBlogSearchResult());
    } catch (JsonProcessingException e) {
      return "";
    }
  }

}
