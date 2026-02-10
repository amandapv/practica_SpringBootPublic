package com.example.ejercicio_7_4;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ejercicio_7_4.domain.Autor;
import com.example.ejercicio_7_4.domain.Curso;
import com.example.ejercicio_7_4.domain.Tematica;
import com.example.ejercicio_7_4.domain.Video;
import com.example.ejercicio_7_4.services.AutorService;
import com.example.ejercicio_7_4.services.CursoService;
import com.example.ejercicio_7_4.services.VideoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(AutorService autorService, CursoService cursoService, VideoService videoService) {
		return args -> {
			//Crear Autores
			Autor kurisu = autorService.añadir(new Autor(null, "Kurisu Makise", "makise@edu.com",5000.0));
			Autor okabe = autorService.añadir(new Autor(null, "Rintaro Okabe", "rintaro@edu.com", 2000.0));

			//Crear Cursos
			// IDs = null porque ya no añadimos el número a mano, si no que lo hace Hibernate ya que así lo definimos en el dominio de Curso
			Curso daw = cursoService.añadir(new Curso (null, "DAW", 2000d, Tematica.SISTEMAS, kurisu, new ArrayList<>())); //dejo a null la lista de videos 
			Curso backend = cursoService.añadir(new Curso(null, "Backend", 500d, Tematica.PROGRAMACION, okabe, new ArrayList<>()));

			//Crear Vídeos
			videoService.añadir(new Video(null, "Conceptos básicos de terminal", 300, "https://www.youtube.com/watch?v=q9gxhNCnmqs", daw), daw.getId());
			videoService.añadir(new Video(null, "Conceptos avanzados de terminal", 500, "https://www.youtube.com/watch?v=q9gxhNCnmqs", daw), daw.getId());
			videoService.añadir(new Video(null, "Conceptos MVA", 800, "https://www.youtube.com/watch?v=q9gxhNCnmqs", backend), backend.getId());
			videoService.añadir(new Video(null, "Conceptos API Rest", 1500, "https://www.youtube.com/watch?v=q9gxhNCnmqs", backend), backend.getId());

		}; 
	}

}
