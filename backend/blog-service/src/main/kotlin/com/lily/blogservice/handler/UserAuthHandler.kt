package com.lily.blogservice.handler

import com.lily.blogservice.data.*
import com.lily.blogservice.entity.User
import com.lily.blogservice.repository.UserRepository
import kotlinx.coroutines.reactive.asFlow
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Component
class UserAuthHandler(val userRepository: UserRepository) {

//    fun register(req: ServerRequest): Mono<ServerResponse> {
//        return req.bodyToMono(SignupRequest::class.java)
//            .flatMap { userRepository.save(map(it)) }
//            .flatMap {
//                ServerResponse.ok()
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(BodyInserters.fromObject(map2(it))) }
//    }
//
    fun map(sr: SignupRequest): User = User(firstName = sr.firstName, lastName = sr.lastName, email = sr.email, password = sr.password, userId = null)
    fun map2(user: User): UserDto = UserDto(id=user.userId!!, name = user.firstName + user.lastName, email = user.email)
//
    suspend fun login(req: ServerRequest): ServerResponse {
        val req = req.awaitBodyOrNull(LoginRequest::class)
        return req?.let {
            val aa = userRepository.findByEmail2(it.email)

            aa?.let {
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(
                        APIResponse(200, "", JwtResponse("1234567", UserDto(1L, aa.firstName + aa.lastName, it.email))))
            } ?: ServerResponse.notFound().buildAndAwait()
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    fun register(req: SignupRequest): Mono<UserDto> = userRepository.save(map(req)).map { map2(it) }

    fun login(req: LoginRequest): Mono<JwtResponse> {
        return userRepository.findByEmail(req.email).map {
            JwtResponse("1234567", UserDto(1L, it.firstName+it.lastName, it.email))
        }.switchIfEmpty(Mono.error(IllegalArgumentException("로그인 실패")))
    }
}