package com.oc.backend.controller;

import com.oc.backend.model.User;
import com.oc.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
//@RequestMapping("/api/user")


public class UserController {
  @Autowired
  private UserService userService;


  @GetMapping("/{id}")
  public User getUserById(@PathVariable Integer id) {
    return userService.getUserById(id);
  }
}
