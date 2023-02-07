package com.lily.blogservice.client

import com.lily.blogservice.data.BlogSearchRequest
import com.lily.blogservice.data.BlogSearchResult
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class BlogSearchClient(val source: BlogSearchSource, val webClient: WebClient) {

    suspend fun search(request: BlogSearchRequest?): BlogSearchResult {
        val uriTemplate = source.getUrlTemplate()
        val headers = source.getHttpHeaders()
        val parameters = source.getParameters(request!!)

        val json = webClient.get()
            .uri(uriTemplate.expand(parameters))
            .headers { httpHeaders ->
                headers.keys.stream().forEach {
                    httpHeaders.set(it, headers.getValue(it))
                }}
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<Map<String, Any>>(){})
            .awaitSingle()// Mono -> Coroutine으로 변환

        val meta = source.parseSearchMeta(json)
        val documents = source.parseBlogDocuments(json)

        return BlogSearchResult(meta, documents)
    }
}