package com.lily.blogservice.repository

import com.lily.blogservice.entity.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveCrudRepository<User, Long> {

    @Query("SELECT u.* FROM users u WHERE u.email = :email")
    fun findByEmail(email: String): Mono<User>

    @Query("SELECT u.* FROM users u WHERE u.email = :email")
    suspend fun findByEmail2(email: String): User?
}