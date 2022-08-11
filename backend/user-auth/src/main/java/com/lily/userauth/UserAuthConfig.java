package com.lily.userauth;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ={"com.lily.userauth.repository"})
@EntityScan(basePackages = {"com.lily.userauth.repository.entity"})
@ComponentScan(basePackages = {"com.lily.userauth"})
public class UserAuthConfig {

}
