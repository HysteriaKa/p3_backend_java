package com.oc.backend.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(Long id){
    super(("Could nont find this user " + id));
  }
}
