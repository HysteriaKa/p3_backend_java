package com.oc.backend.controller;

import com.oc.backend.dto.AuthRequest;
import com.oc.backend.dto.AuthResponse;
import com.oc.backend.dto.RegisterRequest;
import com.oc.backend.service.AuthService;
import com.oc.backend.service.UserService;
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
  private UserService userService;
@PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
  return ResponseEntity.ok(service.register(request));
}
@PostMapping("/login")
public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
  return ResponseEntity.ok(service.authenticate(request));
}
//@GetMapping("/me")
//public UserDetails getMe(@RequestBody UserDetails user){

  //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  //String connected = authentication.getName();
  //user = userService.loadUserByUsername(connected);
   //return (UserDetails) ResponseEntity.ok( user );
//}
}
