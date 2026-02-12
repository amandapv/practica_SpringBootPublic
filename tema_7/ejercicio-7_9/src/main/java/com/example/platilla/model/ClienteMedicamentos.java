package com.example.platilla.model;

public class ClienteMedicamentos extends Paciente {
    private String medicamentos;
    private static int COSTO_POR_MEDICAMENTO = 5; // valor por defecto

    public ClienteMedicamentos(String nombre, String dni, String fechaNacimiento, String medicamentos) {
        this.setNombre(nombre);
        this.setDni(dni);
        this.setFechaNacimiento(fechaNacimiento);
        this.medicamentos = medicamentos;
    }

    @Override
    public int calcularFactura() {
        return medicamentos.split(",").length * COSTO_POR_MEDICAMENTO;
    }
    
    public static void setCostoPorMedicamento(int costo) {
        COSTO_POR_MEDICAMENTO = costo;
    }
}
