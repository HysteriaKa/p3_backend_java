package com.oc.backend.controller;

import com.oc.backend.dto.UserDTO;
import com.oc.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MeController {

  private final UserService userService;

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
