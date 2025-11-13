package com.example.crud_basico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.crud_basico.domain.Curso;
import com.example.crud_basico.domain.Tematica;
import com.example.crud_basico.services.CursoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(CursoService cursoService) {
		return args -> {
			cursoService.añadir(new Curso(1L, "José López", 25000d, Tematica.SISTEMAS));
			cursoService.añadir(new Curso(2L, "Ana García", 20000d, Tematica.PROGRAMACION));

		};
	}

}
