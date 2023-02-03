package com.lily.blogservice.endpoint

import com.lily.blogservice.data.APIResponse
import com.lily.blogservice.data.LoginRequest
import com.lily.blogservice.data.SignupRequest
import com.lily.blogservice.data.SingupResult
import com.lily.blogservice.handler.UserAuthHandler
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerResponse

@RestController
@RequestMapping("/api/v1/user")
class UserController(val handle: UserAuthHandler) {

    @PostMapping("/register")
    fun register(@RequestBody request: SignupRequest) = handle.register(request).map { APIResponse(result = SingupResult(user = it), code = 200) }

//    @PostMapping("/login")
//    fun login(@RequestBody login: LoginRequest) = handle.login(login).map { APIResponse(result = it, code = 200) }.flatMap {
//        ServerResponse.ok()
//        .contentType(MediaType.APPLICATION_JSON)
//        .body(fromObject(it))
//        }
}