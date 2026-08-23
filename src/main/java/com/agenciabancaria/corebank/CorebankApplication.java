package com.agenciabancaria.corebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class CorebankApplication {

	public static void main(String[] args) {

		SpringApplication.run(CorebankApplication.class, args);
	}

}
