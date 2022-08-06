package com.lily.backend.common.config;

import com.lily.backend.security.AccessiblePath;
import com.lily.backend.security.jwt.JwtAuthenticationEntryPoint;
import com.lily.backend.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  private JwtAuthenticationEntryPoint entryPoint;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().antMatchers("/v2/api-docs","/favicon.ico", "/swagger-resources/**", "/swagger-ui.html");
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests() // 권한요청 처리 설정 메서드
        .antMatchers( AccessiblePath.H2_CONSOLE, AccessiblePath.SWAGGER_UI ).permitAll(); // 누구나 h2-console 접속 허용


    http.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers(AccessiblePath.REGISTER, AccessiblePath.LOGIN).permitAll()
        .anyRequest().authenticated().and()
        .exceptionHandling()
        .authenticationEntryPoint(entryPoint)

        // h2-console 을 위한 설정을 추가
        .and()
        .headers()
        .frameOptions()
        .sameOrigin()

        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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
