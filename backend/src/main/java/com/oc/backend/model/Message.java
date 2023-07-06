package com.oc.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
@Table(name = "messages")
@Data
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;
  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  private String message;

  private int rental_id;

  private int user_id;
}
