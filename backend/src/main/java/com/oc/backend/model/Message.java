package com.oc.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="messages")
@Data
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "created_at")
  private Date createdAt;
  @Column(name = "updated_at")
  private Date updatedAt;
  private String message;
  private int rental_id;
  private int user_id;
}
