package com.oc.backend.service;

import com.oc.backend.exception.CustomException;
import com.oc.backend.model.User;
import com.oc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {

  private UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public String signin(String email, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      User user = userRepository.findByEmail(email).orElseThrow();
      String jwtToken = jwtService.generateToken(user);
      return jwtToken;
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
  public String signup(User user){
if(!userRepository.existsByEmail(user.getEmail())){
  user.setPassword(passwordEncoder.encode(user.getPassword()));
  userRepository.save(user);
  return jwtService.generateToken(user);
}else {
  throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
}
  }
  public User whoami(HttpServletRequest req) {
    String email = jwtService.extractUserEmail(req.getHeader("Authorization").substring(7));
    return userRepository.findByEmail(email).get();
    // return userRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }
  public User search(String email) {
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isEmpty()) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return optionalUser.get();
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

    return new User(
      user.getId(),
      user.getEmail(),
      user.getName(),
      user.getPassword(),
      user.getCreatedAt(),
      user.getUpdatedAt());
  }

}
