package com.oc.backend;

import com.oc.backend.model.User;
import com.oc.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
  UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
  @Override
  public void run(String... params) throws Exception {
   User admin = new User();
    admin.setName("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");

    userService.signup(admin);

  }
}
