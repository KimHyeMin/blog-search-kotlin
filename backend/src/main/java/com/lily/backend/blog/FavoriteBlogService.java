package com.lily.backend.blog;


import com.lily.backend.blog.dto.BlogDto;
import com.lily.backend.blog.dto.FavoriteBlogUrl;
import com.lily.backend.blog.entity.FavoriteBlog;
import com.lily.backend.common.exception.AuthenticateException;
import com.lily.backend.common.exception.NoResourceFindException;
import com.lily.backend.common.utills.StringCompressUtil;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Slf4j
@Service
public class FavoriteBlogService {

  @Autowired
  private FavoriteBlogRepository favoriteBlogRepository;

  public boolean addFavorite(Long userId, BlogDto blog) {
    try {
      favoriteBlogRepository.save(map(userId, blog));
      return true;
    } catch (Exception e) {
      log.error("Fail to save favorite blog of user : {}, {}", userId, e);
    }
    return false;
  }

  private FavoriteBlog map(Long userId, BlogDto blog) throws IOException {
    String contents = StringCompressUtil.compress(blog.getContents());

    return FavoriteBlog.builder()
        .userId(userId)
        .blogName(blog.getBlogName())
        .title(blog.getTitle())
        .content(contents)
        .thumbnail(blog.getThumbnail())
        .url(blog.getUrl())
        .urlHashCode(blog.getUrlHashCode())
        .writtenAt(blog.getDatetime())
        .createdAt(LocalDateTime.now())
        .build();
  }


  public List<BlogDto> getMyList(Long userId, @Nullable Long searchAfter) {
    List<FavoriteBlog> myList;
    if (searchAfter == null) {
      myList = favoriteBlogRepository.findFavoriteBlogByUserId(userId, PageRequest.of(0, 10));
    } else {
      myList = favoriteBlogRepository.findFavoriteBlogByUserId(userId, searchAfter, PageRequest.of(0, 10));
    }

    if (myList == null) {
      return new ArrayList<>();
    }
    return myList.stream().map(it -> {
      BlogDto blog = new BlogDto();
      blog.setFavoriteId(it.getFavoriteId());
      blog.setBlogName(it.getBlogName());
      blog.setContents(StringCompressUtil.decompress(it.getContent()));
      blog.setThumbnail(it.getThumbnail());
      blog.setTitle(it.getTitle());
      blog.setUrl(it.getUrl());
      blog.setDatetime(it.getWrittenAt());
      return blog;
    }).collect(Collectors.toList());
  }

  public boolean remove(Long userId, Long blogId) {
    try {
      Optional<FavoriteBlog> blogOpt = favoriteBlogRepository.findById(blogId);
      if (blogOpt.isEmpty()) {
        throw new NoResourceFindException("blog is not found blogId : " + blogId);
      }

      FavoriteBlog favoriteBlog = blogOpt.get();
      if (!userId.equals(favoriteBlog.getUserId())) {
        throw new AuthenticateException(String.format("user %s didnt add blog %s", userId, blogId));
      }

      favoriteBlogRepository.delete(favoriteBlog);
    } catch (Exception e) {
      log.error("Fail to delete favorite blog of user : {}, {}", blogId, e);
    }
    return false;
  }

  public Map<Integer, Long> findFavoriteIds(Long userId, List<Integer> urlHashCodes) {
    List<FavoriteBlogUrl> result =  favoriteBlogRepository.findFavoriteIdMapByUserIdAndUrlHashCode(userId, urlHashCodes);

    if (CollectionUtils.isEmpty(result)) {
      return new HashMap<>();
    }

    return result
          .stream()
          .collect(Collectors.toMap(FavoriteBlogUrl::getUrlHashCode, FavoriteBlogUrl::getFavoriteId));
  }
}
