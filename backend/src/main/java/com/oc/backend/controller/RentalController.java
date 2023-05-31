package com.oc.backend.controller;

import com.oc.backend.dto.RentalDTO;
import com.oc.backend.model.Rental;
import com.oc.backend.service.RentalService;
import com.oc.backend.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

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
    String path = uploadService.getPathFile() + fileName;
    uploadService.storeFile(file,fileName);
    Rental rental = Rental.fromDTO(newRental );
//    System.out.println(path);
     return rentalService.addNewRental(rental);

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
