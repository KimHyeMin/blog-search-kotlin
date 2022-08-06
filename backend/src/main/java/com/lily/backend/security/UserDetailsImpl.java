package com.lily.backend.security;

import com.lily.backend.user.entity.User;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

  private Long id;
  private String username;
  private String email;
  private String password;

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //todo current there is no authority
    return null;
  }

  public static UserDetailsImpl build(User user) {

    return new UserDetailsImpl(
        user.getId(),
        user.getFirstName() + user.getLastName(),
        user.getEmail(),
        user.getPassword());
  }
}
