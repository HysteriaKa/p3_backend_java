package com.oc.backend.service;

import com.oc.backend.exception.CustomException;
import com.oc.backend.model.User;
import com.oc.backend.repository.UserRepository;
import com.oc.backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService  {

  private UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;
  @GetMapping
  public List<User> list(){
    return userRepository.findAll();
  }
  public String signin(String email, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      return jwtTokenProvider.createToken(email);
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
  public String signup(User User){
if(!userRepository.existsByEmail(User.getEmail())){
  User.setPassword(passwordEncoder.encode(User.getPassword()));
  userRepository.save(User);
  return jwtTokenProvider.createToken(User.getEmail());
}else {
  throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
}
  }
  public User search(String email) {
    User User = userRepository.findByEmail(email);
    if (User == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return User;
  }

}
