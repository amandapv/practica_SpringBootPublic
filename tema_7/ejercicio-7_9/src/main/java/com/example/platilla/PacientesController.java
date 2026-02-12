package com.example.platilla; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.platilla.dto.PacienteDTO;
import com.example.platilla.service.PacientesService;



@Controller
public class PacientesController {

    @Autowired
    private PacientesService service;

    @GetMapping("/")
    public String getMethodName(Model model) {
        model.addAttribute("pacientes",service.getPacientes());
        return "listaPacientes";
    }

    @PostMapping("/crearCliente/submit")
    public String postMethodName(PacienteDTO pacienteDTO) {
        System.out.println(pacienteDTO);
        service.savePaciente(pacienteDTO);
        return "redirect:/";
    }

    @GetMapping("/atenderSiguiente")
    public String atenderSiguiente() {
        service.atenderSiguientePaciente();
        return "redirect:/";
    }

    @GetMapping("/crearCliente")
    public String crearCliente(Model model) {
        model.addAttribute("pacienteDTO", new PacienteDTO());
        return "crearCliente";
    }
    
    
    
}
