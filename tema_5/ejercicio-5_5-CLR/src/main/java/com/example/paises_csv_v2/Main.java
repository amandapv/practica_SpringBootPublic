package com.example.paises_csv_v2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.paises_csv_v2.interfaces.ListPaisesService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner cargarCsv(ListPaisesService listPaisesService) {

		return args -> {
			List<Pais> listadoPaises = new ArrayList<>(); //hago un arraylist de tipo Pais (clase que he creado; con su nombre del pais, capital y poblacion) es decir, un arraylist de objetos de tipo Pais

			for (String datosPaises : listPaisesService.cargarPaisesDesdeFichero()) {
            // System.out.println(datosPaises);

				String[] divididos = datosPaises.split(";"); //buscar el caracter ; y la divide en ese punto y la añado al array de String "divididos"

				if (divididos.length == 3) {
					String pobTxt = divididos[2]; //pillo el string de la posicion 3, que será la población, pero ahora es un string
					Integer poblacionInt = Integer.parseInt(pobTxt); //lo convierto a entero para pasarselo a mi objeto nuevoPais de la clase Pais

					Pais nuevoPais = new Pais(divididos[0], divididos[1], poblacionInt); //creo el objeto con los datos
					listadoPaises.add(nuevoPais); //añado ese objeto a mi arraylist de paises
				}
        	}	
			listPaisesService.setListadoPaises(listadoPaises);
		};
	}
}
