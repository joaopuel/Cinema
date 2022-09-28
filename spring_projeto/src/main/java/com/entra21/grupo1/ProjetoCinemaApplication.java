package com.entra21.grupo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjetoCinemaApplication {

	/**
	 * Rosas são vermelhas, violetas são azuis, caso ache algum erro, contate um suporte técnico.
	 */
	public static void main(String[] args) {
			SpringApplication.run(ProjetoCinemaApplication.class, args);
	}
}
