package com.lily.userauth.security;

import com.lily.userauth.repository.UserRepository;
import com.lily.userauth.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findUserByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

    return UserDetailsImpl.build(user);
  }
}
