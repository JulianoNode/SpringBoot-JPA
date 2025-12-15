package com.Strart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaStart {

	public static void main(String[] args) {
		SpringApplication.run(JpaStart.class, args);
		System.err.println("App Rondando...\n");
		System.err.println(
				"🔹 1️ Relacionamento UNIDIRECIONAL\n📌 Nome correto: UNIDIRECIONAL\n➡️ Apenas uma entidade conhece a outra.\n");
		System.err.println(
				"🔹 2️ Relacionamento BIDIRECIONAL\n📌 Nome correto: BIDIRECIONAL\n➡️ As duas entidades se conhecem.");
	}

}
