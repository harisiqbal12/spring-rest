package com.rest.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		System.out.println("Server running at: 8080");;
		SpringApplication.run(RestApplication.class, args);
	}

}
