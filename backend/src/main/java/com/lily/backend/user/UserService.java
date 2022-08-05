package com.lily.backend.user;


import com.lily.backend.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  public UserDto register(SignupRequest signupForm) {
    UserDto userDto = new UserDto();
    userDto.setEmail("apples1309@gmail.com");
    userDto.setName("김혜민");
    return userDto;
  }

}
