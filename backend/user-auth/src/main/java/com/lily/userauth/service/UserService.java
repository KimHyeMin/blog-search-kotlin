package com.lily.userauth.service;

import com.lily.userauth.dto.JwtResponse;
import com.lily.userauth.dto.LoginRequest;
import com.lily.userauth.dto.SignupRequest;
import com.lily.userauth.dto.UserDto;
import com.lily.userauth.exception.SignupFailedException;
import com.lily.userauth.repository.UserRepository;
import com.lily.userauth.repository.entity.User;
import com.lily.userauth.security.UserDetailsImpl;
import com.lily.userauth.security.jwt.JwtUtils;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtils jwtUtils;

  public UserDto register(SignupRequest signupForm) {
    try {
      User save = userRepository.save(map(signupForm));
      return UserDto.builder()
          .id(save.getUserId())
          .name(save.getFirstName() + " " +save.getLastName())
          .email(save.getEmail())
          .build();
    } catch (DataIntegrityViolationException e) {
      throw new SignupFailedException("해당 아이디를 사용할 수 없습니다.");
    } catch (Exception e) {
      throw new SignupFailedException("회원가입에 실패했습니다.");
    }
  }

  private User map(SignupRequest form) {
    return User.builder()
        .firstName(form.getFirstName())
        .lastName(form.getLastName())
        .email(form.getEmail())
        .password(encoder.encode(form.getPassword()))
        .registeredAt(LocalDateTime.now())
        .build();
  }

  public JwtResponse authenticate(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return JwtResponse.builder()
        .token(jwt)
        .user(UserDto.builder()
            .email(userDetails.getEmail())
            .name(userDetails.getUsername())
            .id(userDetails.getId())
            .build())
        .build();
  }
}
