package com.example.platilla.model;

public class ClienteTarifa extends Paciente {
    private String motivoConsulta;
    private static int COSTO_CONSULTA = 100; // valor por defecto

    public ClienteTarifa(String nombre, String dni, String fechaNacimiento, String motivoConsulta) {
        this.setNombre(nombre);
        this.setDni(dni);
        this.setFechaNacimiento(fechaNacimiento);
        this.motivoConsulta = motivoConsulta;
    }

    @Override
    public int calcularFactura() {
        System.out.println(motivoConsulta);
        return COSTO_CONSULTA;
    }
    
    public static void setCostoConsulta(int costo) {
        COSTO_CONSULTA = costo;
    }
}
