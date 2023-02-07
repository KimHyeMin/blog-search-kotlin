package com.lily.blogservice.data

class BlogSearchRequest(val keywords:String,
                        val sort:SearchSort=SearchSort.ACCURACY,
                        val page:Int=1,
                        val size:Int=30,
                        val first:Boolean=false)


enum class SearchSort {
    ACCURACY, RECENCY;
}