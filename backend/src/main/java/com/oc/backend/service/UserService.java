package com.oc.backend.service;


import com.oc.backend.dto.UserDTO;
import com.oc.backend.model.User;
import com.oc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileDescriptor;


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
  public UserDTO getConnectedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String userName = authentication.getName();

    System.out.println(authentication.getName());
      User user = userRepository.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User with this Mail not found"));
    UserDTO userDTO = new UserDTO();
    userDTO.setEmail(user.getEmail());
    userDTO.setId(Long.valueOf(user.getId()));
    userDTO.setName(user.getName());
    userDTO.setCreated_at(user.getCreatedAt());
    userDTO.setUpdated_at(user.getUpdatedAt());
    return userDTO;


    }
    return null;
  }
}
