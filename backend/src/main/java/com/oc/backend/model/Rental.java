package com.oc.backend.model;

import lombok.Data;

import javax.persistence.*;
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
}
