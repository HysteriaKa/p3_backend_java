package com.oc.backend.service;


import com.oc.backend.model.User;
import com.oc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("User Not found with email: " + email));

    return new User(
      user.getId(),
      user.getEmail(),
      user.getName(),
      user.getPassword(),
      user.getCreatedAt(),
      user.getUpdatedAt());

  }
  public User getUserById(Integer id) {
    return userRepository
      .findById(id)
      .orElseThrow();
  }
}
