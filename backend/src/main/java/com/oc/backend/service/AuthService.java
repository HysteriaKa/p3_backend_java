package com.oc.backend.service;

import com.oc.backend.dto.AuthRequest;
import com.oc.backend.dto.AuthResponse;
import com.oc.backend.dto.RegisterRequest;
import com.oc.backend.model.User;
import com.oc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponse register(RegisterRequest request) {
    var user = User.builder()
      .name(request.getName())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthResponse.builder()
      .token(jwtToken)
      .build();
  }

  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getLogin(),
        request.getPassword()
      )
    );
    var user = userRepository.findByEmail(request.getLogin()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthResponse.builder()
      .token(jwtToken)
      .build();
  }

}
