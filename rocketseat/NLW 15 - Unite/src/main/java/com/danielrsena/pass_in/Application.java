package com.danielrsena.pass_in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "PassIn - NLW 15 Unite", version = "1", description = "API para gerenciamento de eventos presenciais"))
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}