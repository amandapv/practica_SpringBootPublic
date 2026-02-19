package com.example.ejercicio_9_10.domain;

import java.util.HashMap;

import lombok.Data;

@Data
public class CambioData {
    private float amount;
    private String base;
    private String date;
    private HashMap<String, Float> rates; //key= moneda destino / valor= tasa cambio
}
