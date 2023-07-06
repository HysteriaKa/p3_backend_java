package com.oc.backend.payload;

import com.oc.backend.model.Rental;

import java.util.List;


public class RentalsResponses {
  private List<Rental> rentals;

  public List<Rental> getRentals() {
    return rentals;
  }

  public void setRentals(List<Rental> rentals) {
    this.rentals = rentals;
  }


}
