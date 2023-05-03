package com.oc.backend.repository;

import com.oc.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
  boolean existsByEmail(String email);

  User findByEmail(String email);

  @Transactional
  void deleteByEmail(String email);
}
