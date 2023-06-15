package com.oc.backend.service;

import com.oc.backend.exception.RentalNotFoundException;
import com.oc.backend.model.Rental;
import com.oc.backend.repository.RentalRepository;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Service;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class RentalService {

  private RentalRepository rentalRepository;
  public RentalService(RentalRepository rentalRepository) {
    this.rentalRepository = rentalRepository;
  }
  public Rental getRentalById(Long id) {
    return rentalRepository
      .findById(id)
      .orElseThrow(() -> new RentalNotFoundException(id));
  }
  public Iterable<Rental> getAllRentals() {
    // List<Rental> rentals = new ArrayList<Rental>();
    // rentalRepository.findAll().forEach(rental->rentals.add(rental));
    // return rentals;

    return rentalRepository.findAll();
  }
  public Rental addNewRental(Rental newRental){

    return rentalRepository.save(newRental);
  }
  public Rental updateRental(Rental rentalUpdate, Long id) {
    return rentalRepository
      .findById(id)
      .map(rental -> {
        rental.setName(rentalUpdate.getName());
        rental.setDescription(rentalUpdate.getDescription());
        rental.setPicture(rentalUpdate.getPicture());
        rental.setSurface(rentalUpdate.getSurface());
        rental.setOwner_id(rentalUpdate.getOwner_id());
        return rentalRepository.save(rental);
      })
      .orElseGet(() -> {
        rentalUpdate.setId(id);
        return rentalRepository.save(rentalUpdate);
      });
  }
}
