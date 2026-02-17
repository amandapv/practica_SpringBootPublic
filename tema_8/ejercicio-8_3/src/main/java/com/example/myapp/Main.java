package com.example.myapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.myapp.domain.Cuenta;
import com.example.myapp.domain.Rol;
import com.example.myapp.domain.Usuario;
import com.example.myapp.services.CuentaService;
import com.example.myapp.services.UserService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(CuentaService cuentaService, UserService userService) {
		return args -> {
			cuentaService.añadir(new Cuenta("iban1", "alias1", 0));
			cuentaService.añadir(new Cuenta("iban2", "alias2", 0));

			userService.añadir(new Usuario(null, "Panda", "1234", Rol.ADMIN));
			userService.añadir(new Usuario(null, "Kyoma", "1234", Rol.TITULAR));
			userService.añadir(new Usuario(null, "Amanda", "1234", Rol.USUARIO));
		};
	}

}
