package com.hms.user.UserMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.user.UserMS.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  static Optional<User> findByEmail(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
}
}
