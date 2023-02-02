package com.lily.blogservice.handler

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class BlogSearchHandler {

    suspend fun search(req: ServerRequest): ServerResponse {
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .bodyValueAndAwait("Blog Search Controller search")
    }

    suspend fun getKeywords(req: ServerRequest): ServerResponse {
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .bodyValueAndAwait("Blog Search Controller getKeywords")
    }
}