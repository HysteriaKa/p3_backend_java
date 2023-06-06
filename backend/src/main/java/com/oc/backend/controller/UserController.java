package com.oc.backend.controller;

import com.oc.backend.model.User;
import com.oc.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  @Operation(summary = "Get one User by his Id")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Return one User",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = User.class)) }),
    @ApiResponse(responseCode = "403", description = "Invalid credentials",
      content = {@Content(mediaType = "application/json",
        schema=@Schema(implementation = User.class)) }),
    @ApiResponse(responseCode = "404", description = "User not found",
      content = @Content) })
  public User getUserById(@PathVariable Integer id) {
    return userService.getUserById(id);
  }
}
