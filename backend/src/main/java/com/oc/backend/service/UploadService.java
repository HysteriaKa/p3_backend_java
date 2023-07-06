package com.oc.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class UploadService {

  @Value("${upload.path.img}")
  private String uploadPath;


  public String saveFile(MultipartFile file) {
    if (file == null) {
      return "http://localhost:8080/api/" + uploadPath + "/" + "file_not_exist";

    }

    File uploadDirectory = new File(uploadPath);
    if (!uploadDirectory.exists()) {
      uploadDirectory.mkdirs();
    }
    try {
      Path paths = Paths.get(uploadPath).toAbsolutePath().normalize();
      Path targetLocation = paths.resolve(Objects.requireNonNull(file.getOriginalFilename()));

      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return "http://localhost:8080/api/" + uploadPath + "/" + file.getOriginalFilename();
    } catch (IOException e) {
      throw new NotFoundException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
    }

  }

}
