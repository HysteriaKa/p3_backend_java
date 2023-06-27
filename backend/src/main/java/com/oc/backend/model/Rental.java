package com.oc.backend.model;

import com.oc.backend.dto.RentalDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.nio.file.Paths;
import java.sql.Date;

@Entity
@Table(name="rentals")
@Data
public class Rental {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String name;
  private int surface;
  private double price;
  @NotBlank
  private String picture;
  @NotBlank
  private String description;
  private int owner_id;
  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;
  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;
}
