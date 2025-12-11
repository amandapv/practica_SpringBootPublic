package com.example.cuestionario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cuestionario.domain.Curso;
import com.example.cuestionario.domain.Tematica;
import com.example.cuestionario.services.CursoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(CursoService cursoService) {
		return args -> {
			cursoService.añadir(new Curso(1L, "Backend", 25000d, Tematica.SISTEMAS));
			cursoService.añadir(new Curso(2L, "Frontend", 20000d, Tematica.PROGRAMACION));

		};
	}

}
