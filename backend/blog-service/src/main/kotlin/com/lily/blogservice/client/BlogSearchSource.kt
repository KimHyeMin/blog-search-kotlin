package com.lily.blogservice.client

import com.lily.blogservice.data.BlogDocument
import com.lily.blogservice.data.BlogSearchRequest
import com.lily.blogservice.data.SearchMeta
import com.lily.blogservice.data.SearchSort
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.util.UriTemplate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

interface BlogSearchSource {

    fun getUrlTemplate():UriTemplate

    fun getHttpHeaders(): HttpHeaders

    fun sortToQueryParam(sort: SearchSort):String

    fun getParameters(request: BlogSearchRequest):Map<String, Any>

    fun parseSearchMeta(json: Map<String, Any>): SearchMeta

    fun parseBlogDocuments(json: Map<String, Any>): List<BlogDocument>
}

class KakaoBlogSearchSource(private val blogSearchUrl:String, private val clientToken:String): BlogSearchSource {

    override fun getUrlTemplate(): UriTemplate = UriTemplate("$blogSearchUrl?query={query}&sort={sort}&page={page}&size={size}")

    override fun getHttpHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", clientToken);
        return headers
    }

    override fun sortToQueryParam(sort: SearchSort): String = sort.name.lowercase()

    override fun getParameters(request: BlogSearchRequest): Map<String, Any> {
        val parameters = HashMap<String, Any>()
        parameters["query"] = request.keywords
        parameters["sort"] = request.sort
        parameters["page"]= request.page
        parameters["size"]= request.size
        return parameters;
    }

    override fun parseSearchMeta(json: Map<String, Any>): SearchMeta {
        val metaJson = json["meta"] as Map<String, Any>
        val tc:String? = metaJson["total_count"]?.toString()
        val pc:String? = metaJson["pageable_count"]?.toString()
        val ie:String? = metaJson["is_end"]?.toString()
        return SearchMeta( tc?.toInt() ?: 0,pc?.toInt() ?: 0, ie?.toBoolean() ?: false)
    }

    override fun parseBlogDocuments(json: Map<String, Any>): List<BlogDocument> {
        val list = json["documents"]  as? List<Map<String, String>>

        return list!!.stream().map { parseBlogDocument(it) }.collect(Collectors.toList())
    }

    fun parseBlogDocument(docJson: Map<String, String>):BlogDocument {
        val postDate = docJson.getOrDefault("datetime", "");
        return BlogDocument(
            favoriteId = null,
            title = docJson.getOrDefault("title", ""),
            contents = docJson.getOrDefault("contents", ""),
            url = docJson.getOrDefault("url", ""),
            thumbnail = docJson.getOrDefault("thumbnail", ""),
            blogName = docJson.getOrDefault("blogname", ""),
            datetime = LocalDateTime.parse(postDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        )
    }

}