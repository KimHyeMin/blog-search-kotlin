package com.lily.blogservice.handler

import com.lily.blogservice.data.APIResponse
import com.lily.blogservice.data.FavoriteBlogResult
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json

@Component
class FavoriteHandler {


    suspend fun getFavoriteList(req: ServerRequest): ServerResponse {
        val criterias = req.queryParams()
        val searchAfter:Long = when {
            criterias.isEmpty() -> 0
            criterias.contains("searchAfter") -> {
                val searchAfter = criterias.getFirst("searchAfter")
                if (searchAfter.isNullOrBlank()) 0
                else searchAfter.toLong()
            }
            else -> 0
        }
        val userId:Long = req.pathVariable("userId").toLong()
        val result = FavoriteBlogResult(listOf())
        val apiResponse = APIResponse(code=200, "", result = result)
        return ServerResponse.ok().json().bodyValueAndAwait(apiResponse)
    }
}