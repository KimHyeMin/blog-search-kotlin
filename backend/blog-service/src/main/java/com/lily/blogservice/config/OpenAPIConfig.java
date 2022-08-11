package com.lily.blogservice.config;

import com.lily.blogservice.client.KakaoBlogSearchSource;
import com.lily.blogservice.client.NaverBlogSearchSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

  @Value("${client.kakao.token}")
  private String kakaoClientToken;

  @Value("${client.kakao.url}")
  private String kakaoBlogSearchUrl;

  @Value("${client.naver.client.id}")
  private String naverClientId;

  @Value("${client.naver.client.secret}")
  private String naverClientSecret;

  @Value("${client.naver.url}")
  private String naverBlogSearchUrl;

  @Bean
  public KakaoBlogSearchSource kakaoBlogSearchSource() {
    return new KakaoBlogSearchSource(kakaoClientToken, kakaoBlogSearchUrl);
  }

  @Bean
  public NaverBlogSearchSource naverBlogSearchSource() {
    return new NaverBlogSearchSource(naverClientId, naverClientSecret, naverBlogSearchUrl);
  }

}
