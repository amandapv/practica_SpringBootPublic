package com.example.paises_csv_v2.interfaces;

import java.util.List;

import com.example.paises_csv_v2.Pais;

public interface ListPaisesService {

    List<String> cargarPaisesDesdeFichero();

    // List<Pais> mostrarPaises(List<String> ficheroCSV);
    void setListadoPaises(List<Pais> listado); 
    List<Pais> getListadoPaises();

    Pais buscarPaisDatos(String nomPais);
}
