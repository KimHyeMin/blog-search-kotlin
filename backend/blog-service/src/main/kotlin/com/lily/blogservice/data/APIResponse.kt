package com.lily.blogservice.data

class APIResponse<T>(
    val code:Int,
    val errorMessage:String="",
    val result:T?) {

    companion object {
        fun fail(error:String) = APIResponse(200, error, null)
    }
}