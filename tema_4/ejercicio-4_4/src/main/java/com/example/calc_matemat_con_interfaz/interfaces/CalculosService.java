package com.example.calc_matemat_con_interfaz.interfaces;

import java.util.ArrayList;

public interface CalculosService {
    
    boolean calcularPrimo(String numPrimo);

    Double calcularHipotenusa(String cat1, String cat2);

    ArrayList<Integer> calcularDivisores(String numDiv);

}