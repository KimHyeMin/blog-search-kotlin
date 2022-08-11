package com.lily.blogservice;

import com.lily.blogservice.dto.BlogDocument;
import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.BlogSearchResult;
import com.lily.blogservice.dto.SearchMeta;
import com.lily.blogservice.dto.SearchSort;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearchDummyData {

  public final static String TEST_KEYWORDS = "TEST_KEYWORDS";
  public final static SearchSort TEST_SEARCH_SORT = SearchSort.ACCURACY;
  public final static int TEST_PAGE = 1;
  public final static int TEST_SIZE = 30;

  public static BlogSearchRequest testBlogSearchRequest() {
    BlogSearchRequest request = new BlogSearchRequest();
    request.setKeywords(TEST_KEYWORDS);
    request.setSort(TEST_SEARCH_SORT);
    request.setPage(TEST_PAGE);
    request.setSize(TEST_SIZE);

    return request;
  }

  public static BlogDocument testBlogDocument() {
    BlogDocument blogDocument = new BlogDocument();

    final int testId = (int) (Math.random() * 10000);
    blogDocument.setFavoriteId(Long.valueOf(testId));
    blogDocument.setBlogName("blogname" + testId);
    blogDocument.setTitle("title" + testId);
    blogDocument.setContents("contents" + testId);
    blogDocument.setUrl("https://testblog.com/" + testId);
    blogDocument.setThumbnail("https://testthumbnail.com/" + testId + ".jpg");
    blogDocument.setDatetime(LocalDateTime.now());

    return blogDocument;
  }

  public static BlogSearchResult testBlogSearchResult() {
    SearchMeta meta = new SearchMeta();
    meta.setIsEndPage(true);
    meta.setPageableCount(0);
    meta.setTotalCount(0);

    List<BlogDocument> blogDocuments = new ArrayList<>();
    for (int i = 0; i < 11; i++) {
      blogDocuments.add(testBlogDocument());
    }

    return new BlogSearchResult(meta,blogDocuments);
  }

}
