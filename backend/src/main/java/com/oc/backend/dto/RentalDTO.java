package com.oc.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;

@Data
public class RentalDTO implements Serializable {
  private Long id;
  @NotBlank
  private String name;
  private Date created_at;
  private Date updated_at;
  @NotBlank
  private int surface;
  @NotBlank
  private int price;
  @NotBlank
  private String description;
  @NotBlank
  private int owner_id;
  @NotBlank
  private MultipartFile picture;

  public RentalDTO(){};

  public RentalDTO(Long id, String name, Date created_at, Date updated_at, int surface, int price, String description, int owner_id, MultipartFile picture ) {
    this.id = id;
    this.name = name;
    this.created_at =created_at;
    this.updated_at = updated_at;
    this.price = price;
    this.description = description;
    this.surface = surface;
    this.picture = picture;
    this.owner_id = owner_id;
  }
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public int getSurface() {
    return surface;
  }

  public void setSurface(int surface) {
    this.surface = surface;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getOwner_id() {
    return owner_id;
  }

  public void setOwner_id(int owner_id) {
    this.owner_id = owner_id;
  }

  public MultipartFile getPicture() {
    return picture;
  }

  public void setPicture(MultipartFile picture) {
    this.picture = picture;
  }
}

