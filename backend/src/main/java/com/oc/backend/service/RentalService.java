package com.oc.backend.service;

import com.oc.backend.exception.RentalNotFoundException;
import com.oc.backend.model.Rental;
import com.oc.backend.model.User;
import com.oc.backend.repository.RentalRepository;
import com.oc.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
  @Autowired
  private RentalRepository rentalRepository;
  @Autowired
  private UserRepository userRepository;

  public Rental getRentalById(Long id) {
    return rentalRepository
      .findById(id)
      .orElseThrow(() -> new RentalNotFoundException(id));
  }

  public Iterable<Rental> getAllRentals() {
    return rentalRepository.findAll();
  }

  public Rental addNewRental(Rental newRental) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    User user = userRepository.findByEmail(currentUserName).get();
    newRental.setOwner_id(user.getId());
    return rentalRepository.save(newRental);
  }

  public Rental updateRental(Rental rentalUpdate, Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    User user = userRepository.findByEmail(currentUserName).get();
    return rentalRepository
      .findById(id)
      .map(rental -> {
        rental.setName(rentalUpdate.getName());
        rental.setDescription(rentalUpdate.getDescription());
        rental.setSurface(rentalUpdate.getSurface());
        rental.setOwner_id(user.getId());
        return rentalRepository.save(rental);
      })
      .orElseGet(() -> {
        rentalUpdate.setId(id);
        return rentalRepository.save(rentalUpdate);
      });
  }
}
