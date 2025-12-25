package com.Strart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaStart {
	public static void main(String[] args) {
		Organizacao organizacao = new Organizacao();
		SpringApplication.run(JpaStart.class, args);
		
		organizacao.Run();
	}

}
