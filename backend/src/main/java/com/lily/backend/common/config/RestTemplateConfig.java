package com.lily.backend.common.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {

  @Bean
  CloseableHttpClient httpClient() {
    return HttpClientBuilder.create()
        .setMaxConnTotal(100)
        .setMaxConnPerRoute(5)
        .build();
  }

  @Bean
  HttpComponentsClientHttpRequestFactory factory(CloseableHttpClient httpClient) {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setReadTimeout(3000);
    factory.setConnectTimeout(1000);
    factory.setHttpClient(httpClient);

    return factory;
  }

  @Bean
  RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory factory) {
    return new RestTemplate(factory);
  }

}