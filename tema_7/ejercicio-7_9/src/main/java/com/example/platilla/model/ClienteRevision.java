package com.example.platilla.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRevision extends Paciente {
    private String fechaVisitaAnterior;
    private static int COSTO_POR_REVISION = 50; // valor por defecto
    private static int COSTO_POR_REVISION_JUBILADO = 30; // valor por defecto

    public ClienteRevision(String nombre, String dni, String fechaNacimiento, String fechaVisitaAnterior) {
        this.setNombre(nombre);
        this.setDni(dni);
        this.setFechaNacimiento(fechaNacimiento);
        this.fechaVisitaAnterior = fechaVisitaAnterior;
    }

    @Override
    public int calcularFactura() {
        return isJubilado() ? COSTO_POR_REVISION_JUBILADO : COSTO_POR_REVISION;
    }

    private boolean isJubilado() {
        // Lógica simplificada para determinar si el paciente es jubilado
        // Aquí se podría implementar una lógica real basada en la fecha de nacimiento
        return true; // Suponemos que todos son jubilados para este ejemplo
    }
    
    public static void setCostoPorRevision(int costo) {
        COSTO_POR_REVISION = costo;
    }
    
    public static void setCostoPorRevisionJubilado(int costo) {
        COSTO_POR_REVISION_JUBILADO = costo;
    }
}
