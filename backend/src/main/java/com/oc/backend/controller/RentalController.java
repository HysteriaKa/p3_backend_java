package com.oc.backend.controller;

import com.oc.backend.dto.RentalDTO;
import com.oc.backend.model.Rental;
import com.oc.backend.payload.RentalsResponses;
import com.oc.backend.service.RentalService;
import com.oc.backend.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
  @Operation(summary = "Get all rentals")
  @SecurityRequirement(name = "Bearer Authentication")
  public RentalsResponses getAllRentals() {
    RentalsResponses rentalsResponses = new RentalsResponses();
    rentalsResponses.setRentals(StreamSupport.stream(rentalService.getAllRentals().spliterator(), false)
      .collect(Collectors.toList()));
    return rentalsResponses;

  }

  @GetMapping("/{id}")
  @Operation(summary = "Get rental by its id")
  @SecurityRequirement(name = "Bearer Authentication")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Found the rental",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Rental.class))}),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
      content = @Content),
    @ApiResponse(responseCode = "403", description = "JWT not found",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "Rental not found",
      content = @Content)})
  public Rental getOneRental(@Parameter(description = "id of rental to be searched") @PathVariable Long id) {
    return rentalService.getRentalById(id);
  }

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  @Operation(summary = "Post a new Rental")
  @SecurityRequirement(name = "Bearer Authentication")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "new rental created",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = RentalDTO.class))}),
    @ApiResponse(responseCode = "500", description = "Server Error",
      content = @Content),
    @ApiResponse(responseCode = "403", description = "JWT not found",
      content = @Content),
   })
  public Rental addNewRental(@ModelAttribute("rental") RentalDTO rentalDTO) {
    String pictureUrl = uploadService.saveFile(rentalDTO.getPicture());
    Rental rental = rentalDTO.fromDTO(rentalDTO);
    rental.setPicture(pictureUrl);
    return rentalService.addNewRental(rental);

  }

  @PutMapping(value="/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  @Operation(summary = "Update a rental by its id")
  @SecurityRequirement(name = "Bearer Authentication")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Rental updated",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Rental.class))}),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
      content = @Content),
    @ApiResponse(responseCode = "403", description = "JWT not found",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "Rental not found",
      content = @Content)})
  public Rental updateRental(@ModelAttribute("rental") RentalDTO rentalDTO, @PathVariable Long id) {
    Rental rental = rentalDTO.fromDTO(rentalDTO);
    return rentalService.updateRental(rental, id);
  }

}
