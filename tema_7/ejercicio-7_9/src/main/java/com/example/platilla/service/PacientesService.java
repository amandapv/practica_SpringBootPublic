package com.example.platilla.service;

import java.util.List;

import com.example.platilla.dto.PacienteDTO;
import com.example.platilla.model.Paciente;

public interface PacientesService {
    public void savePaciente(PacienteDTO dto);
    public List<Paciente> getPacientes();
    public void atenderSiguientePaciente();
}
