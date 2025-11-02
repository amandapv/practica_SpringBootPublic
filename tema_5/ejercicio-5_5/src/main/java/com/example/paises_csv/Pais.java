package com.example.paises_csv;

import lombok.Getter;
import lombok.Setter;

//hacer los getter y setter usando Lombok
@Getter
@Setter

public class Pais {
    private String nomPais;
    private String capital;
    private Integer poblacion;

    //crear el constructor
    public Pais(String nomPais, String capital, Integer poblacion) {
        this.nomPais = nomPais;
        this.capital = capital;
        this.poblacion = poblacion;
    }
}