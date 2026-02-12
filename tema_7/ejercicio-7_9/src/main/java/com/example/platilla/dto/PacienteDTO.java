package com.example.platilla.dto;

import com.example.platilla.model.ClienteMedicamentos;
import com.example.platilla.model.ClienteRevision;
import com.example.platilla.model.ClienteTarifa;
import com.example.platilla.model.Paciente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {
    // Datos comunes de paciente
    private String nombre;
    private String dni;
    private String fechaNacimiento;
    
    // Tipo de paciente
    private String tipoPaciente;
    
    // Datos extra según el tipo
    private String datosExtra;
    
    /**
     * Método que crea el objeto Paciente específico según el tipo
     * @return Paciente (ClienteMedicamentos, ClienteRevision o ClienteTarifa)
     */
    public Paciente crearPaciente() {
        // Utiliza el método crearPaciente del DTO para crear la instancia adecuada
        Paciente paciente = null;
        
        switch (tipoPaciente) {
            case "medicamentos":
                paciente = new ClienteMedicamentos(nombre, dni, fechaNacimiento, datosExtra);
                break;
            case "revision":
                paciente = new ClienteRevision(nombre, dni, fechaNacimiento, datosExtra);
                break;
            case "tarifa":
                paciente = new ClienteTarifa(nombre, dni, fechaNacimiento, datosExtra);
                break;
        }
        
        return paciente;
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", tipoPaciente='" + tipoPaciente + '\'' +
                ", datosExtra='" + datosExtra + '\'' +
                '}';
    }
}
