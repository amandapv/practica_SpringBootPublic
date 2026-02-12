package com.example.platilla.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.platilla.dto.PacienteDTO;
import com.example.platilla.model.ClienteMedicamentos;
import com.example.platilla.model.ClienteRevision;
import com.example.platilla.model.ClienteTarifa;
import com.example.platilla.model.Paciente;

@Service
public class PacientesServiceImpl implements PacientesService {
    private final List<Paciente> pacientes = new ArrayList<>();

    public PacientesServiceImpl() {
        // Crear 3 pacientes por defecto, uno de cada tipo
        pacientes.add(
                new ClienteMedicamentos("Juan Pérez", "12345678A", "15/03/1980", "Paracetamol,Ibuprofeno,Omeprazol"));
        pacientes.add(new ClienteRevision("María García", "87654321B", "22/07/1955", "10/10/2024"));
        pacientes.add(new ClienteTarifa("Carlos López", "11223344C", "05/12/1990", "Dolor de cabeza"));
    }

    @Override
    public void savePaciente(PacienteDTO dto) {
        // Utiliza el método crearPaciente del DTO para crear la instancia adecuada
        Paciente paciente = dto.crearPaciente();

        if (paciente != null) {
            pacientes.add(paciente);
        }
    }

    @Override
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    @Override
    public void atenderSiguientePaciente() {
        if (pacientes.size() <= 0)
            return;
        Paciente paciente = pacientes.get(0);
        ///llama al metodo calcular factura y dependiendo 
        // de la clase que sea imprime el resultado por consola
        System.out.println("El precio por atenderlo es "+paciente.calcularFactura());
        pacientes.remove(0);
    }

}
