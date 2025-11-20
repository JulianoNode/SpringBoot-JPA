package com.JPA_Start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StrartApp {

	public static void main(String[] args) {
		SpringApplication.run(StrartApp.class, args);
		System.err.println(" Systema rodando na porta:8080...");
	}

}
