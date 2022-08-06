package com.lily.backend.user;


import com.lily.backend.user.dto.UserDto;
import com.lily.backend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserDto register(SignupRequest signupForm) {
    User save = userRepository.save(map(signupForm));
    return UserDto.builder()
        .name(save.getFirstName() + " " +save.getLastName())
        .email(save.getEmail())
        .build();
  }

  private User map(SignupRequest form) {
    return User.builder()
        .firstName(form.getFirstName())
        .lastName(form.getLastName())
        .email(form.getEmail())
        .password(form.getPassword())
        .build();
  }

}
