package com.oc.backend.controller;

import com.oc.backend.dto.AuthRequest;
import com.oc.backend.dto.AuthResponse;
import com.oc.backend.dto.RegisterRequest;
import com.oc.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;
@PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
  return ResponseEntity.ok(service.register(request));
}
@PostMapping("/login")
public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
  return ResponseEntity.ok(service.authenticate(request));
}
//@GetMapping("/me")
//public UserDetails getMe(@RequestBody UserDetails userDetails){
  //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  //userDetails = (UserDetails) authentication.getPrincipal();
  //return null;
//}
}
