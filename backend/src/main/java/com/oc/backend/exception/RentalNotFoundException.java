package com.oc.backend.exception;

public class RentalNotFoundException extends RuntimeException{
  public RentalNotFoundException(Long id) {
    super("Could not find this rental " + id);
  }
}
