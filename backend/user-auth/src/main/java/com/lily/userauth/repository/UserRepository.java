package com.lily.userauth.repository;

import com.lily.userauth.repository.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findUserByEmail(String email);

}
