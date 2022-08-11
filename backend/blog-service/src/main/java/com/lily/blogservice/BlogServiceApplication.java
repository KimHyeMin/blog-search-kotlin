package com.lily.blogservice;

import com.lily.trends.TrendConfig;
import com.lily.userauth.UserAuthConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(value = {UserAuthConfig.class, TrendConfig.class})
@SpringBootApplication
public class BlogServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BlogServiceApplication.class, args);
  }

}
