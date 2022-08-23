package com.lily.userauth.security;

import com.lily.userauth.security.jwt.JwtAuthenticationEntryPoint;
import com.lily.userauth.security.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class GlobalSecurityConfig {

  private final JwtRequestFilter jwtRequestFilter;

  private final JwtAuthenticationEntryPoint entryPoint;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().antMatchers(
        "/h2-console/**"
        ,"/favicon.ico"
        ,"/error",
        "/swagger-ui/**",
        "/v2/api-docs",
        "/swagger-resources/**",
        "/css/**", "/js/**"
    );
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(entryPoint)

        // h2-console 을 위한 설정을 추가
        .and()
        .headers()
        .frameOptions()
        .sameOrigin()

        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
        .authorizeRequests()
        .antMatchers(AccessiblePath.MAIN).permitAll()
        .antMatchers(AccessiblePath.REGISTER, AccessiblePath.LOGIN).permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

}
