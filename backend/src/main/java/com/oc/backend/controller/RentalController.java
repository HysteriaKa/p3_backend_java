package com.oc.backend.controller;

import com.oc.backend.dto.RentalDTO;
import com.oc.backend.model.Rental;
import com.oc.backend.service.RentalService;
import com.oc.backend.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  @Autowired
  private RentalService rentalService;
  @Autowired
  private final UploadService uploadService;

  public RentalController(UploadService uploadService) {
    this.uploadService = uploadService;
  }


  @GetMapping
  public Iterable<Rental> getAllRentals(){
    return rentalService.getAllRentals();
  }
  @GetMapping("/{id}")
  public Rental getOneRental(@PathVariable Long id){
    return rentalService.getRentalById(id);
  }
  @PostMapping
  public Rental addNewRental(@ModelAttribute RentalDTO newRental, @RequestParam("picture") MultipartFile file) {
    String fileName = file.getOriginalFilename();
    System.out.println(fileName);
    System.out.println(newRental);
    file = new UploadService(newRental.getPicture());
// return rentalService.addNewRental(newRental);
return null;
  }
  @PutMapping("/{id}")
  public Rental updateRental(@RequestBody Rental updateRental, @PathVariable Long id) {
    return rentalService.updateRental(updateRental, id);
  }
//  public ResponseEntity<UploadResponse> uploadFile(
//    @RequestParam(name = "file", required = false) MultipartFile file,
//    @RequestParam("fullName") String fullName,
//
//  ) {
//    String fileName = uploadService.storeFile(file);
//
//    UploadResponse uploadResponse = new UploadResponse(fileName, fullName);
//
//    return ResponseEntity.ok().body(uploadResponse);
//  }
}
