package com.oc.backend.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)

public class SwaggerConfig {
  @Bean
  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
      .group("app")
      .pathsToMatch("/**")
      .build();
  }

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
      .info(new Info().title("Projet3 API")
        .description("Projet3")
        .version("v0.0.1")
        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
      .externalDocs(new ExternalDocumentation()
        .description("Projet3 Wiki Documentation")
        .url("https://springshop.wiki.github.org/docs"));
  }

}
