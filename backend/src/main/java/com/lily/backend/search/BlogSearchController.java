package com.lily.backend.search;

import com.lily.backend.common.dto.APIResponse;
import com.lily.backend.search.dto.BlogSearchRequest;
import com.lily.backend.search.dto.BlogSearchResult;
import com.lily.backend.search.dto.ResultSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/search")
public class BlogSearchController {

  @Autowired
  private BlogSearchService blogSearchService;

  @GetMapping(
      value = "/blogs",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public APIResponse<BlogSearchResult> searchBlogs(
      @RequestParam
          String keywords,
      @RequestParam
              ResultSort sort,
      @Min(1) @Max(1000)
      @RequestParam(defaultValue = "1")
          int page,
      @Min(0) @Max(500)
      @RequestParam(defaultValue = "30")
          int size
  ) {
    //todo keywords validation
    final BlogSearchRequest request = new BlogSearchRequest(keywords, sort, page, size);

    try {
      final BlogSearchResult result = blogSearchService.searchBlogs(request);

      return APIResponse.<BlogSearchResult>builder()
              .code(HttpStatus.OK.value())
              .result(result)
              .build();
    } catch (Exception e) {
      log.error("Exception occurred on search blogs API. e: ", e);
      //todo Exception handling here or create common exception handling controller
      return APIResponse.<BlogSearchResult>builder()
              .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .build();
    }

  }

}
