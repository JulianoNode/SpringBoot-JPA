package com.Strart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaRelacaoTabelaLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaRelacaoTabelaLoginApplication.class, args);
		System.out.println("");
		System.out.println("** Logar como ADMINISTRADOR no systema **");
		System.err.println("   Email: admin@gmail.com");
		System.err.println("   Senha: 123");
	}

}
