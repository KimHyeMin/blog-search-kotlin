package com.lily.blogservice.endpoint;

import com.lily.blogservice.dto.BlogSearchRequest;
import com.lily.blogservice.dto.BlogSearchResult;
import com.lily.blogservice.dto.FrequentResult;

import com.lily.blogservice.service.BlogSearchService;
import com.lily.blogservice.service.FrequentKeywordService;
import com.lily.trends.dto.FrequentKeyword;
import com.lily.userauth.dto.APIResponse;
import com.lily.userauth.security.CurrentUser;
import com.lily.userauth.security.UserDetailsImpl;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/search")
public class BlogSearchController {

  private final BlogSearchService blogSearchService;

  private final FrequentKeywordService frequentKeywordService;

  @GetMapping(value = "/blogs", produces = MediaType.APPLICATION_JSON_VALUE)
  public APIResponse<BlogSearchResult> searchBlogs(@ModelAttribute @Valid final BlogSearchRequest request,
      @CurrentUser UserDetailsImpl user) {

    final BlogSearchResult result = blogSearchService.searchBlogs(request, user);

    return APIResponse.success(result);
  }

  @GetMapping(value = "/frequent/keywords", produces = MediaType.APPLICATION_JSON_VALUE)
  public APIResponse<FrequentResult> getTopKeywords() {
    List<FrequentKeyword> top10 = frequentKeywordService.getList();
    FrequentResult result = new FrequentResult(top10);
    return APIResponse.success(result);
  }

}
