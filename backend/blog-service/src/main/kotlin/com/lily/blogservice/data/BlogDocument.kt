package com.lily.blogservice.data

class BlogDocument(
    val favoriteId:Long?,
    val blogName:String="",
    val title:String="",
    val contents:String="",
    val url:String="",
    val thumbnail:String="",
    val datetime:String?,
    ) {

    fun getUrlHashCode():Int = url.hashCode()
}