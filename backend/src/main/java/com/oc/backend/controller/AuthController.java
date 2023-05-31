package com.oc.backend.controller;

import com.oc.backend.dto.AuthRequest;
import com.oc.backend.dto.AuthResponse;
import com.oc.backend.dto.RegisterRequest;
import com.oc.backend.dto.UserDTO;
import com.oc.backend.service.AuthService;
import com.oc.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;
  private final UserService userService;
@PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
  return ResponseEntity.ok(service.register(request));
}
@PostMapping("/login")
public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
  return ResponseEntity.ok(service.authenticate(request));
}
  @GetMapping("/me")
  public ResponseEntity<?> getMe() {
    UserDTO userDTO = userService.getConnectedUser();
    if (userDTO != null) {
      return ResponseEntity.ok(userDTO);
    }
    return ResponseEntity
      .badRequest()
      .body("Unknown User !");
  }

}
