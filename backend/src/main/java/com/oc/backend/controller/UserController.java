package com.oc.backend.controller;

import com.oc.backend.dto.UserDataDTO;
import com.oc.backend.dto.UserResponseDTO;
import com.oc.backend.model.User;
import com.oc.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final ModelMapper modelMapper;
@PostMapping("/auth/login")
  public String login( @RequestParam String email, @RequestParam String password){

    return userService.signin(email, password);
  }

  @PostMapping("/auth/register")
  public String signup(@RequestBody UserDataDTO user) {
    return userService.signup(modelMapper.map(user, User.class));
  }

  @GetMapping(value = "/{email}")
  public UserResponseDTO search(@PathVariable String email) {
    return modelMapper.map(userService.search(email), UserResponseDTO.class);
  }
}
