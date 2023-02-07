package com.lily.blogservice.data

data class UserDto(
    val id: Long,
    val name: String,
     val email: String)


class UserDetails(
    val id:Long,
    val username:String,
    val email:String,
    val password:String,
)