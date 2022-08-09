package com.lily.backend.blog.response;

import com.lily.backend.blog.dto.BlogDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FavoriteBlogResult {

  private List<BlogDto> blogList;

}
