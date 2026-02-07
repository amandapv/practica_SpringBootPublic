package com.example.myapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.myapp.domain.Departamento;
import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;
import com.example.myapp.services.DepartamentoService;
import com.example.myapp.services.EmpleadoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
//Acceso a la consola H2  http://localhost:9000/h2-console
	@Bean
	CommandLineRunner initData(EmpleadoService empleadoService, DepartamentoService departamentoService) {
		return args -> {
			Departamento depInf = departamentoService.añadir(new Departamento());
			Departamento depRRHH = departamentoService.añadir(new Departamento());

			empleadoService.añadir(
					new Empleado(null, "López Pérez, José", "jlp@gmail.com", 18000d, true, Genero.MASCULINO, depInf));
			empleadoService.añadir(new Empleado(null, "García Martínez, Ana", "ana_garcia@gmail.com", 20000d, false,
					Genero.FEMENINO, depInf));
			empleadoService.añadir(new Empleado(null, "Martínez Ruiz, Juan", "juanmr@gmail.com", 25000d, true,
					Genero.MASCULINO, depInf));
			empleadoService.añadir(
					new Empleado(null, "Pérez Gómez, María", "mariapg@gmail.com", 18000d, true, Genero.FEMENINO,
							depInf));
			empleadoService.añadir(new Empleado(null, "Gómez Sánchez, Pedro", "pedro_sanchez@gmail.com", 22000d, false,
					Genero.MASCULINO, depInf));
			empleadoService.añadir(new Empleado(null, "Sánchez López, Laura", "laurasl@gmail.com", 27000d, true,
					Genero.FEMENINO, depRRHH));
			empleadoService.añadir(
					new Empleado(null, "Ruiz García, David", "davidruiz@gmail.com", 43000d, false, Genero.OTROS,
							depRRHH));
			empleadoService.añadir(new Empleado(null, "Pérez Martínez, Carmen", "carmenpm@gmail.com", 29000d, true,
					Genero.OTROS, depRRHH));
			empleadoService.añadir(new Empleado(null, "Martínez Gómez, Sergio", "sergiomg@gmail.com", 19000d, false,
					Genero.MASCULINO, depRRHH));
			empleadoService.añadir(new Empleado(null, "López Sánchez, Isabel", "isalopez@gmail.com", 24000d, true,
					Genero.FEMENINO, depRRHH));

		};
	}

}
