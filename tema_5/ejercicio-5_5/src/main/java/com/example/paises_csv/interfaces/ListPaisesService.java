package com.example.paises_csv.interfaces;

import java.util.List;

import com.example.paises_csv.Pais;

public interface ListPaisesService {

    List<String> cargarPaisesDesdeFichero();

    List<Pais> mostrarPaises(List<String> ficheroCSV);

    Pais buscarPaisDatos(String nomPais);
}
