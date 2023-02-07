package com.lily.blogservice

import com.lily.blogservice.client.BlogSearchClient
import com.lily.blogservice.data.BlogSearchRequest
import com.lily.blogservice.data.BlogSearchResult
import com.lily.blogservice.data.UserDetails
import org.springframework.stereotype.Service

@Service
class BlogSearchService(val client: BlogSearchClient) {

    suspend fun searchBlogs(breq: BlogSearchRequest?, user: UserDetails?): BlogSearchResult = client.search(breq)
}