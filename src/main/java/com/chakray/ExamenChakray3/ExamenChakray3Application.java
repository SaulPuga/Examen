package com.chakray.ExamenChakray3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ExamenChakray3Application {

	public static void main(String[] args) {
		SpringApplication.run(ExamenChakray3Application.class, args);
	}

	@GetMapping
	public String hola(){
		return "hola desde el main";
	}
}
