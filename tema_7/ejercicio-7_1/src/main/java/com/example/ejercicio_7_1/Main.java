package com.example.ejercicio_7_1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ejercicio_7_1.domain.Curso;
import com.example.ejercicio_7_1.domain.Tematica;
import com.example.ejercicio_7_1.services.CursoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(CursoService cursoService) {
		return args -> {
			cursoService.añadir(new Curso(1L, "DAW", 2000d, Tematica.SISTEMAS));
			cursoService.añadir(new Curso(2L, "Backend", 500d, Tematica.PROGRAMACION));

		}; 
	}

}
