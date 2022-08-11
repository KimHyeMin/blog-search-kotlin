package com.lily.userauth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import com.lily.userauth.dto.JwtResponse;
import com.lily.userauth.dto.LoginRequest;
import com.lily.userauth.security.UserDetailsImpl;
import com.lily.userauth.security.jwt.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


@ExtendWith(MockitoExtension.class)
class UserAuthenticateTest {

    @InjectMocks
    private UserService dut;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;


    UsernamePasswordAuthenticationToken buildValidTestToken() {
        UserDetailsImpl user = new UserDetailsImpl(1l, "Basic User", "ex3@company.com", "password");
        return new UsernamePasswordAuthenticationToken(user, null);
    }

    @Test
    public void mustGetAuthenticatedUser() {
        LoginRequest input = new LoginRequest();
        input.setEmail("ex@company.com");
        input.setPassword("password");

        doReturn(buildValidTestToken())
            .when(authenticationManager)
            .authenticate(any(UsernamePasswordAuthenticationToken.class));
        doReturn("json web token")
            .when(jwtUtils)
            .generateJwtToken(any(Authentication.class));
        JwtResponse actual = dut.authenticate(input);

        assertEquals(actual.getUser().getName(), "Basic User");
        assertEquals(actual.getUser().getId(), 1L);
        assertEquals(actual.getUser().getEmail(), "ex3@company.com");
        assertNotNull(actual.getToken());
    }

    @Test
    public void mustThrowUnauthenticatedUser() {
        LoginRequest input = new LoginRequest();
        input.setEmail("ex@company.com");
        input.setPassword("password");

        doThrow(new BadCredentialsException("dd"))
            .when(authenticationManager)
            .authenticate(any(Authentication.class));

        Exception exception = assertThrows(BadCredentialsException.class, () -> dut.authenticate(input));

        assertEquals(exception.getClass(), BadCredentialsException.class);
    }
}