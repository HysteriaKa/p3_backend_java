package com.oc.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDTO implements Serializable {
  private Long id;

  private String email;

  private String name;

  private LocalDate created_at;

  private LocalDate updated_at;

  public UserDTO() {
  }

  public UserDTO(Long id, String email, String name, LocalDate createdAt, LocalDate updatedAt) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.created_at = createdAt;
    this.updated_at = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDate created_at) {
    this.created_at = created_at;
  }

  public LocalDate getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(LocalDate updated_at) {
    this.updated_at = updated_at;
  }

}
