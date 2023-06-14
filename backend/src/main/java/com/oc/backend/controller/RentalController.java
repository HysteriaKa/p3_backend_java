package com.oc.backend.controller;

import com.oc.backend.dto.RentalDTO;
import com.oc.backend.model.Rental;
import com.oc.backend.service.RentalService;
import com.oc.backend.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  @Autowired
  private RentalService rentalService;
  @Autowired
  private  UploadService uploadService;

  public RentalController(UploadService uploadService) {
    this.uploadService = uploadService;
  }


  @GetMapping
  @Operation(summary = "Get all rentals")
  public Iterable<Rental> getAllRentals(){
    return rentalService.getAllRentals();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a book by its id")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Found the rental",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Rental.class)) }),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
      content = @Content),
    @ApiResponse(responseCode = "403", description = "JWT not found",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "Rental not found",
      content = @Content) })
  public Rental getOneRental(@Parameter(description = "id of rental to be searched") @PathVariable Long id){
    return rentalService.getRentalById(id);
  }

  @PostMapping
  @Operation(summary = "Post a new Rental")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Add a new rental",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = RentalDTO.class)) }),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
      content = @Content),
    @ApiResponse(responseCode = "403", description = "JWT not found",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "Rental not found",
      content = @Content) })
  public Rental addNewRental(@ModelAttribute("rental") RentalDTO rentalDTO) {
    String pictureUrl = uploadService.saveFile(rentalDTO.getPicture());
    Rental rental = rentalDTO.fromDTO(rentalDTO);
    rental.setPicture(pictureUrl);
    return rentalService.addNewRental(rental);

  }
  @PutMapping("/{id}")
  @Operation(summary = "Update a rental by its id")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Rental updated",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Rental.class)) }),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
      content = @Content),
    @ApiResponse(responseCode = "403", description = "JWT not found",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "Rental not found",
      content = @Content) })
  public Rental updateRental(@RequestBody Rental updateRental, @PathVariable Long id) {
    return rentalService.updateRental(updateRental, id);
  }

}
