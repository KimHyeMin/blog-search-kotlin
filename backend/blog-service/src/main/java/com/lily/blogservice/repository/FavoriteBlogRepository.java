package com.lily.blogservice.repository;

import com.lily.blogservice.dto.FavoriteBlogUrl;
import com.lily.blogservice.repository.entity.FavoriteBlog;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FavoriteBlogRepository extends CrudRepository<FavoriteBlog, Long> {

  @Query(value = "select f " +
      "from FavoriteBlog f " +
      "where f.userId = :userId " +
      "order by f.favoriteId desc")
  List<FavoriteBlog> findFavoriteBlogByUserId(@Param("userId") Long userId, Pageable pageable);


  @Query(value = "select f " +
      "from FavoriteBlog f " +
      "where f.userId = :userId and f.favoriteId < :favoriteId " +
      "order by f.favoriteId desc")
  List<FavoriteBlog> findFavoriteBlogByUserId(@Param("userId") Long userId, @Param("favoriteId") Long favoriteId, Pageable pageable);


  @Query(value = "select new com.lily.blogservice.dto.FavoriteBlogUrl(f.urlHashCode, f.favoriteId) "
      + "from FavoriteBlog f "
      + "where f.userId = :userId "
      + "and f.urlHashCode in (:urlHashCodes)")
  List<FavoriteBlogUrl> findFavoriteIdMapByUserIdAndUrlHashCode(@Param("userId") Long userId, @Param("urlHashCodes") List<Integer> urlHashCodes);

}
