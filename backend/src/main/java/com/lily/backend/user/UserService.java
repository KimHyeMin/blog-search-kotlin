package com.lily.backend.user;

import com.lily.backend.common.exception.SignupFailedException;
import com.lily.backend.security.UserDetailsImpl;
import com.lily.backend.security.jwt.JwtUtils;
import com.lily.backend.user.dto.UserDto;
import com.lily.backend.user.entity.User;
import com.lily.backend.user.request.LoginRequest;
import com.lily.backend.user.request.SignupRequest;
import com.lily.backend.user.response.JwtResponse;
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
