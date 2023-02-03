package com.lily.blogservice

import com.lily.blogservice.handler.UserAuthHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AuthRouter(val handler: UserAuthHandler) {

    @Bean
    fun router2Fun() = coRouter{
        "/api/v1/user".nest {
            POST("/login", handler::login)
        }
    }
}