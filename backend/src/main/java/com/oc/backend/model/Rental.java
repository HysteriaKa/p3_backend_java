package com.oc.backend.model;

import com.oc.backend.dto.RentalDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.nio.file.Paths;
import java.sql.Date;

@Entity
@Table(name="rentals")
@Data
public class Rental {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private int surface;
  private double price;
  private String picture;
  private String description;
  private int owner_id;
  @Column(name = "created_at")
  private Date createdAt;
  @Column(name = "updated_at")
  private Date updatedAt;

  public static Rental fromDTO(RentalDTO dto) {
    Rental rental = new Rental();
    rental.setId(dto.getId());
    rental.setName(dto.getName());
    rental.setCreatedAt(dto.getCreated_at());
    rental.setUpdatedAt(dto.getUpdated_at());
    rental.setSurface(dto.getSurface());
    rental.setPrice(dto.getPrice());
    rental.setDescription(dto.getDescription());
    rental.setOwner_id(dto.getOwner_id());
    rental.setPicture(Paths.get( "./uploads/files").toAbsolutePath().normalize() + dto.getPicture().getOriginalFilename());
    return rental;
  }
}
