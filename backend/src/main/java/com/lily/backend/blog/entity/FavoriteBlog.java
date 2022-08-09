package com.lily.backend.blog.entity;

import com.lily.backend.user.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FAVORITES")
public class FavoriteBlog {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long favoriteId;

  @Column(name = "userId")
  private Long userId;

  @ManyToOne(
      targetEntity = User.class,
      fetch = FetchType.LAZY,
      optional = false
  )
  @JoinColumn(name = "userId", insertable = false, updatable = false)
  private User user;

  @Column
  private String title;
  @Column
  private String content;
  @Column
  private String thumbnail;
  @Column
  private String blogName;
  @Column
  private String url;
  @Column
  private LocalDateTime writtenAt;
  @Column
  private LocalDateTime createdAt;

}
