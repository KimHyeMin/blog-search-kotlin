package com.lily.blogservice.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class BlogSearchHandler {

    suspend fun search(req: ServerRequest): ServerResponse {
        return ServerResponse.ok().bodyValueAndAwait("Blog Search Controller")
    }
}