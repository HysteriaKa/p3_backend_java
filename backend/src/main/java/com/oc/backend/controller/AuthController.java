package com.oc.backend.controller;

import com.oc.backend.dto.AuthRequest;
import com.oc.backend.dto.AuthResponse;
import com.oc.backend.dto.RegisterRequest;
import com.oc.backend.dto.UserDTO;
import com.oc.backend.service.AuthService;
import com.oc.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;
  private final UserService userService;

  @PostMapping("/register")
  @Operation(summary = "Register new User")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Register User",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = RegisterRequest.class))}),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "Bad Request",
      content = @Content)})
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  @Operation(summary = "Connect user to the app")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Return a JWT Token",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = AuthRequest.class))}),
    @ApiResponse(responseCode = "403", description = "Invalid credentials",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "User not found",
      content = @Content)})
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {

    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/me")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Get connected User")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Get connected User",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = AuthRequest.class))}),
    @ApiResponse(responseCode = "403", description = "Not Authorised",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = AuthResponse.class))}),
    @ApiResponse(responseCode = "404", description = "User not found",
      content = @Content)})
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
