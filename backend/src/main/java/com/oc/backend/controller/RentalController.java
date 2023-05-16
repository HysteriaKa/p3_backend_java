package com.oc.backend.controller;

import com.oc.backend.model.Rental;
import com.oc.backend.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @GetMapping
  public Iterable<Rental> getAllRentals(){
    return rentalService.getAllRentals();
  }
  @GetMapping("/{id}")
  public Rental getOneRental(@PathVariable Long id){
    return rentalService.getRentalById(id);
  }
  @PostMapping
  public Rental addNewRental(@RequestBody Rental newRental) {
    return rentalService.addNewRental(newRental);
  }
  @PutMapping("/{id}")
  public Rental updateRental(@RequestBody Rental updateRental, @PathVariable Long id) {
    return rentalService.updateRental(updateRental, id);
  }
}
