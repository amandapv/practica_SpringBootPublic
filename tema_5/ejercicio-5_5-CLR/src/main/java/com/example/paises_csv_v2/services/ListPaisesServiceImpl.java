package com.example.paises_csv_v2.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.paises_csv_v2.Pais;
import com.example.paises_csv_v2.interfaces.ListPaisesService;

@Service
public class ListPaisesServiceImpl implements ListPaisesService{

    List<Pais> listadoPaises = new ArrayList<>(); //hago un arraylist de tipo Pais (clase que he creado; con su nombre del pais, capital y poblacion) es decir, un arraylist de objetos de tipo Pais


    public void setListadoPaises(List<Pais> listado) {
        listadoPaises = listado;
    }
    public List<Pais> getListadoPaises() {
        return listadoPaises;
    }


    public List<String> cargarPaisesDesdeFichero() throws RuntimeException {
    
        Path ubicacionPaisesCsv = Paths.get("tema_5/ejercicio-5_5/src/main/resources/paises.csv"); //obtener la ruta del archivo "paises.csv"

        try {
            List<String> arrayFicheroCsv = Files.readAllLines(ubicacionPaisesCsv); // le indicamos donde está el archivo a leer
            
            //otra opción para leer la ubicación del archcivo paises.csv
            //List<String> arrayFicheroCsv2 = Files.readAllLines(ResourceUtils.getFile("classpath:paises.csv").toPath());
            
            // System.out.println("Archivo leído desde: " + ubicacionPaisesCsv.toAbsolutePath());
            // System.out.println("Archivo CSV: " + arrayFicheroCsv);

            return arrayFicheroCsv; //retornar un array con el listado de strings que ha leído del arachivo csv
        }
        catch (IOException e) { //si no encuentra el archivo lanzará una excepción
            throw new RuntimeException("No se encuentra el archivo paises.csv (ni en classpath ni en " + ubicacionPaisesCsv.toAbsolutePath() + ")");
        }
    }


    // public List<Pais> mostrarPaises(List<String> ficheroCSV) {

    //     for (String datosPaises : ficheroCSV) {
    //         // System.out.println(datosPaises);

    //         String[] divididos = datosPaises.split(";"); //buscar el caracter ; y la divide en ese punto y la añado al array de String "divididos"

    //         if (divididos.length == 3) {
    //             String pobTxt = divididos[2]; //pillo el string de la posicion 3, que será la población, pero ahora es un string
    //             Integer poblacionInt = Integer.parseInt(pobTxt); //lo convierto a entero para pasarselo a mi objeto nuevoPais de la clase Pais

    //             Pais nuevoPais = new Pais(divididos[0], divididos[1], poblacionInt); //creo el objeto con los datos
    //             listadoPaises.add(nuevoPais); //añado ese objeto a mi arraylist de paises
    //         }
    //     }
    //     // System.out.println(listadoPaises);

    //     return listadoPaises; //me retorno el listado de paises
    // }


    //métidi para buscar el nombre del pais que seleccione el usuario
    public Pais buscarPaisDatos(String nomPais) throws RuntimeException{

        for (Pais pais : listadoPaises) {
            // System.out.println(pais.getNomPais
            if (pais.getNomPais().equals(nomPais)) { //si el nombre del pais que le paso por parámetro es igual al nombre del objeto del pais actual (es decir, si encontró ese nombre...)
                // System.out.println("CAPITAL DEL PAIS SELECCIONADO" + pais.getCapital());
                return pais; //si encontró el nombre retornará el pais
            }
        }
        System.out.println(nomPais);
        //si ha llegado hasta aquí y no ha encontrado el nombre del pais lanzará una excepción
        throw new RuntimeException("No se encuentra el pais seleccionado");
    }

    
}
