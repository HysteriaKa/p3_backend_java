package com.oc.backend.service;

import com.oc.backend.exception.RentalNotFoundException;
import com.oc.backend.model.Rental;
import com.oc.backend.repository.RentalRepository;
import org.springframework.stereotype.Service;

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
  public Iterable<Rental> getAllRentals(){
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