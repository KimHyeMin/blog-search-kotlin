package com.lily.backend.blog;

import com.lily.backend.blog.dto.BlogDto;
import com.lily.backend.blog.response.FavoriteBlogResult;
import com.lily.backend.blog.response.SimpleFavoriteResult;
import com.lily.backend.common.dto.APIResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/users/{userId}")
public class FavoriteController {

  @Autowired
  private FavoriteBlogService favoriteBlogService;

  @PostMapping(value = "/favorites")
  public APIResponse<SimpleFavoriteResult> addFavorite(@PathVariable Long userId,
                                                       @RequestBody final BlogDto blog) {

    boolean success = favoriteBlogService.addFavorite(userId, blog);

    SimpleFavoriteResult result = new SimpleFavoriteResult(success);

    return APIResponse.success(result);
  }

  @GetMapping("/favorites/list")
  public APIResponse<FavoriteBlogResult> getMyFavoriteList(@PathVariable Long userId,
      @RequestParam(required = false) Long searchAfter) {

    List<BlogDto> list = favoriteBlogService.getMyList(userId, searchAfter);
    FavoriteBlogResult result = new FavoriteBlogResult();
    result.setBlogList(list);

    return APIResponse.success(result);
  }

  @DeleteMapping(value = "/favorites/{favoriteId}")
  public APIResponse<SimpleFavoriteResult> removeFavorite(@PathVariable Long userId, @PathVariable Long favoriteId) {

    boolean success = favoriteBlogService.remove(userId, favoriteId);

    SimpleFavoriteResult result = new SimpleFavoriteResult(success);
    return APIResponse.success(result);
  }

}
