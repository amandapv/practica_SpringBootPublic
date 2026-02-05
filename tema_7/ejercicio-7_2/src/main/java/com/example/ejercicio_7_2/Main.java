package com.example.ejercicio_7_2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ejercicio_7_2.domain.Curso;
import com.example.ejercicio_7_2.domain.Tematica;
import com.example.ejercicio_7_2.services.CursoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(CursoService cursoService) {
		return args -> {
			// IDs = null porque ya no añadimos el número a mano, si no que lo hace Hibernate ya que así lo definimos en el dominio de Curso
			cursoService.añadir(new Curso(null, "DAW", 2000d, Tematica.SISTEMAS));
			cursoService.añadir(new Curso(null, "Backend", 500d, Tematica.PROGRAMACION));

		}; 
	}

}
