package com.lily.blogservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("USERS")
data class User(
    @Id
    @Column("id")
    val userId: Long?,
    @Column("firstName")
    val firstName:String,
    @Column("lastName")
    val lastName:String,
    @Column("email")
    val email:String,
    @Column("password")
    val password:String,
    @Column("registeredAt")
    val registeredAt: LocalDateTime= LocalDateTime.now()
)