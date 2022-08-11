package com.lily.userauth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import com.lily.userauth.dto.SignupRequest;
import com.lily.userauth.dto.UserDto;
import com.lily.userauth.exception.SignupFailedException;
import com.lily.userauth.repository.UserRepository;
import com.lily.userauth.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;


@ExtendWith(MockitoExtension.class)
class UserRegisterTest {

  @InjectMocks
  private UserService dut;

  @Mock
  private UserRepository repository;

  @Mock
  private PasswordEncoder encoder;

  @Test
  public void mustThrowWhenEmailDuplicated() {
    final SignupRequest input = mockInput();

    doThrow(new DataIntegrityViolationException("duplicated index"))
        .when(repository)
        .save(any(User.class));
    doReturn("any password")
        .when(encoder)
        .encode(any(String.class));

    Exception exception = assertThrows(SignupFailedException.class, () -> {
      dut.register(input);
    });

    assertEquals(exception.getClass(), SignupFailedException.class);
  }

  @Test
  public void mustThrowWhenPasswordEncoderHaveError() {
    final SignupRequest input = mockInput();

    doThrow(new IllegalArgumentException())
        .when(encoder)
        .encode(any(String.class));

    Exception exception = assertThrows(SignupFailedException.class, () -> {
      dut.register(input);
    });

    assertEquals(exception.getClass(), SignupFailedException.class);

  }

  @Test
  public void mustRegisterUser() {
    final SignupRequest input = mockInput();
    doReturn("any password")
        .when(encoder)
        .encode(any(String.class));
    doReturn(mockUser())
        .when(repository)
        .save(any(User.class));

    UserDto actual = dut.register(input);
    assertEquals(actual.getName(), mockUser().getFirstName() + " " + mockUser().getLastName());
    assertEquals(actual.getEmail(), mockUser().getEmail());
    assertEquals(actual.getId(), mockUser().getUserId());
  }

  private SignupRequest mockInput() {
    SignupRequest input = new SignupRequest();
    input.setPassword("1234");
    input.setFirstName("first");
    input.setLastName("last");
    input.setEmail("email");
    return input;
  }

  private User mockUser() {
    User user = new User();
    user.setUserId(1L);
    user.setFirstName("first");
    user.setLastName("last");
    user.setEmail("email");
    return user;
  }
}