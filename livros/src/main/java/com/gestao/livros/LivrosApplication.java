package com.gestao.livros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrosApplication.class, args);
	}

}
