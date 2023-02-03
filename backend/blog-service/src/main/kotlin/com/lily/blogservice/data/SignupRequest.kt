package com.lily.blogservice.data

data class SignupRequest (
    val firstName:String,
    val lastName:String,
    val email:String,
    val password:String)