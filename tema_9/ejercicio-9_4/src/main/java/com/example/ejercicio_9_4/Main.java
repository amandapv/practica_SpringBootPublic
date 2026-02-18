package com.example.ejercicio_9_4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ejercicio_9_4.domain.Autor;
import com.example.ejercicio_9_4.domain.Curso;
import com.example.ejercicio_9_4.domain.Tematica;
import com.example.ejercicio_9_4.services.AutorService;
import com.example.ejercicio_9_4.services.CursoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(CursoService cursoService, AutorService autorService) {
		return args -> {
			Autor panda = new Autor(null, "Panda", "panda@gmail.com", 3000.0);
			autorService.añadir(panda);
			Autor kyoma = new Autor(null, "Kyoma", "kyoma@gmail.com", 1000.0);
			autorService.añadir(kyoma);
			// IDs = null porque ya no añadimos el número a mano, si no que lo hace Hibernate ya que así lo definimos en el dominio de Curso
			cursoService.añadir(new Curso(null, "DAW", 2000d, Tematica.SISTEMAS, panda));
			cursoService.añadir(new Curso(null, "Backend", 500d, Tematica.PROGRAMACION, kyoma));

		}; 
	}

}
