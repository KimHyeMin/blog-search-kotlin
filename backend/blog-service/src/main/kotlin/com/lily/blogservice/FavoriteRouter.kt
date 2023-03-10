package com.lily.blogservice

import com.lily.blogservice.handler.FavoriteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class FavoriteRouter(val handler: FavoriteHandler) {
    @Bean
    fun favoriteFun() = coRouter {
        "/api/v1/users/{userId}".nest {
            GET("/favorites/list", handler::getFavoriteList)
        }
    }
}