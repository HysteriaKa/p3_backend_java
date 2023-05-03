package com.oc.backend.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
  private int id;
  private String email;
  private String password;
  private String name;
}
