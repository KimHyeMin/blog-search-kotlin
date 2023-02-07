package com.lily.blogservice.data

import java.time.LocalDateTime

class BlogDocument(
    val favoriteId:Long?,
    val blogName:String="",
    val title:String="",
    val contents:String="",
    val url:String="",
    val thumbnail:String="",
    val datetime: LocalDateTime,
    ) {

    fun getUrlHashCode():Int = url.hashCode()
}