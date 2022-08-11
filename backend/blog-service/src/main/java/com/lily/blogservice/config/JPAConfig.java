package com.lily.blogservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages ={"com.lily.blogservice.repository"})
@EntityScan(basePackages = {"com.lily.blogservice.repository.entity"})
@Configuration
public class JPAConfig {


}
