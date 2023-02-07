package com.lily.blogservice.handler

import com.lily.blogservice.BlogSearchService
import com.lily.blogservice.data.*
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBodyOrNull
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class BlogSearchHandler(val blogSearchService: BlogSearchService) {

    suspend fun search(req: ServerRequest): ServerResponse {
        val breq = BlogSearchRequest(
            keywords = req.queryParam("keywords").orElse(""),
            sort = SearchSort.valueOf(req.queryParam("sort").orElse("DESC")),
            page = req.queryParam("page")?.get()!!.toInt(),
            size = req.queryParam("size")?.get()!!.toInt(),
            first = req.queryParam("first")?.get().toBoolean())
        val user: UserDetails? = req.awaitBodyOrNull(UserDetails::class)
        val result: BlogSearchResult = blogSearchService.searchBlogs(breq, user)
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .bodyValueAndAwait(APIResponse(200, "", result))
    }

    suspend fun getKeywords(req: ServerRequest): ServerResponse {
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .bodyValueAndAwait("Blog Search Controller getKeywords")
    }
}