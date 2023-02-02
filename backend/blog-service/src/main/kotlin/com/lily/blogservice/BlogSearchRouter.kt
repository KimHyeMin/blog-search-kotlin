package com.lily.blogservice

import com.lily.blogservice.handler.BlogSearchHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter


@Configuration
class BlogSearchRouter(val handler: BlogSearchHandler) {

    @Bean
    fun routerFun() = coRouter {
        "/api/v1/search".nest {
            GET("/blogs", handler::search)
            GET("/frequent/keywords", handler::getKeywords)
        }
    }
}