package com.oc.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
@Table(name = "rentals")
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
  @CreationTimestamp
  @Column(name = "created_at")
  private Date created_at;
  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updated_at;
}
