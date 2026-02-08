package com.example.ejercicio_7_3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ejercicio_7_3.domain.Autor;
import com.example.ejercicio_7_3.domain.Curso;
import com.example.ejercicio_7_3.domain.Tematica;
import com.example.ejercicio_7_3.services.AutorService;
import com.example.ejercicio_7_3.services.CursoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(AutorService autorService, CursoService cursoService) {
		return args -> {
			Autor kurisu = autorService.añadir(new Autor(null, "Kurisu Makise", "makise@edu.com",5000.0));
			Autor okabe = autorService.añadir(new Autor(null, "Rintaro Okabe", "rintaro@edu.com", 2000.0));
			// IDs = null porque ya no añadimos el número a mano, si no que lo hace Hibernate ya que así lo definimos en el dominio de Curso
			cursoService.añadir(new Curso(null, "DAW", 2000d, Tematica.SISTEMAS, kurisu));
			cursoService.añadir(new Curso(null, "Backend", 500d, Tematica.PROGRAMACION, okabe));
		}; 
	}

}
