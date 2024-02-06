package com.hillogy.LibraryManagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Clase de configuración para la generación de la especificación OpenAPI.
 * Define la información básica de la API.
 * Autor: oscaralejandroflorez@gmail.com
 * Fecha: 05/01/2024
 * Descripción: Esta clase configura los metadatos de la especificación OpenAPI,
 * incluyendo el título, la versión, la descripción y la información de la licencia.
 */
@Configuration
public class OpenApiConfig {

	/**
	 * Define la configuración personalizada de la especificación OpenAPI.
	 *
	 * @return OpenAPI configurado con metadatos personalizados.
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Library Management System") // Título de la API
						.version("0.1") // Versión de la API
						.description("The system should allow librarians to add, remove, and search for books.") // Descripción de la API
						.termsOfService("http://swagger.io/terms/") // Términos de servicio
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))); // Información de la licencia
	}
}
