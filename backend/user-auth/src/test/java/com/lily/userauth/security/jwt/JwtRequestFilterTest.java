package com.lily.userauth.security.jwt;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.lily.userauth.security.UserDetailsImpl;
import com.lily.userauth.security.UserDetailsServiceImpl;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
class JwtRequestFilterTest {

  @InjectMocks
  private JwtRequestFilter dut;

  @Mock
  private JwtUtils jwtUtils;

  @Mock
  private UserDetailsServiceImpl userDetailsService;

  @BeforeEach
  public void setup() {
    SecurityContextHolder.getContext().setAuthentication(null);
  }

  @Test
  public void mustNotAuthenticatedWhenJWTTokenNotParsed()  throws Exception {
    HttpServletRequest request = requestWithWrongFormatToken();
    HttpServletResponse response = mock(HttpServletResponse.class);
    FilterChain filterChain = mock(FilterChain.class);

    dut.doFilterInternal(request, response, filterChain);

    Authentication actual = SecurityContextHolder.getContext().getAuthentication();
    assertNull(actual);

    verify(jwtUtils, never()).validateJwtToken(anyString());
    verify(userDetailsService, never()).loadUserByUsername(anyString());
  }

  @Test
  public void mustNotAuthenticatedWhenUnValidJWTToken()  throws Exception {
    HttpServletRequest request = requestWithUnValidToken();
    HttpServletResponse response = mock(HttpServletResponse.class);
    FilterChain filterChain = mock(FilterChain.class);

    doReturn(false)
        .when(jwtUtils)
        .validateJwtToken(anyString());

    dut.doFilterInternal(request, response, filterChain);

    Authentication actual = SecurityContextHolder.getContext().getAuthentication();
    assertNull(actual);

    verify(userDetailsService, never()).loadUserByUsername(anyString());
  }

  @Test
  public void mustAuthenticatedWhenValidJWTTokenForValidUser() throws Exception {
    HttpServletRequest request = requestWithValidToken();
    HttpServletResponse response = mock(HttpServletResponse.class);
    FilterChain filterChain = mock(FilterChain.class);

    doReturn(true)
        .when(jwtUtils)
            .validateJwtToken(anyString());
    doReturn(testUser().getEmail())
        .when(jwtUtils)
            .getEmailFromJwtToken(anyString());
    doReturn(testUser())
        .when(userDetailsService)
            .loadUserByUsername(eq(testUser().getEmail()));

    dut.doFilterInternal(request, response, filterChain);

    Authentication actual = SecurityContextHolder.getContext().getAuthentication();
    assertTrue(actual.isAuthenticated());
    assertNotNull(actual.getPrincipal());
  }

  private HttpServletRequest requestWithWrongFormatToken() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    doReturn("temp")
        .when(request)
        .getHeader("Authorization");
    return request;
  }

  private HttpServletRequest requestWithValidToken() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    doReturn("Bearer temp")
        .when(request)
        .getHeader("Authorization");
    return request;
  }

  private HttpServletRequest requestWithUnValidToken() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    doReturn("Bearer wrong")
        .when(request)
        .getHeader("Authorization");
    return request;
  }

  private UserDetailsImpl testUser() {
    return new UserDetailsImpl(1l, "test", "test@email.com", "pw");
  }
}