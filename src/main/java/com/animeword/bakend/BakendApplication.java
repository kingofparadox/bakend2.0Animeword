package com.animeword.bakend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BakendApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
		System.out.println("=== VERIFICANDO VARIABLES DE ENTORNO ===");
        System.out.println("DB_URL leída: " + System.getProperty("DB_URL"));
        System.out.println("DB_USER leído: " + System.getProperty("DB_USER"));
        System.out.println("DB_PASSWORD leído: " + System.getProperty("DB_PASSWORD"));
        System.out.println("========================================");

		SpringApplication.run(BakendApplication.class, args);
	}

}
