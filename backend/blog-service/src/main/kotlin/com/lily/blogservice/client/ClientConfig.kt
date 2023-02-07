package com.lily.blogservice.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class ClientConfig {

    @Value("\${client.kakao.token}")
    lateinit var kakaoClientToken:String

    @Value("\${client.kakao.url}")
    lateinit var kakaoBlogSearchUrl:String

    @Bean
    fun source(): BlogSearchSource = KakaoBlogSearchSource(kakaoBlogSearchUrl, kakaoClientToken)

    @Bean
    fun webClient(): WebClient = WebClient.builder().build()

}