package com.example.platilla.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Paciente {
    private String nombre;
    private String dni;
    private String fechaNacimiento;

    private static Map<String, Integer> tarifas = new HashMap<>();

    public static Map<String, Integer> getTarifas() {
        return new HashMap<>(tarifas); // Retorna copia para evitar modificaciones externas
    }

    public static void setTarifas(Map<String, Integer> nuevasTarifas) {
        tarifas = new HashMap<>(nuevasTarifas);
    }

    public abstract int calcularFactura();
}
