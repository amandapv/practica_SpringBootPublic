package com.example.ejerImplementar;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // anotación controlador
public class HomeController {
    
    @GetMapping({"/", "/home", "/app"})            // ruta a la que responde por GET
    public String showHome( 

            // @RequestParam(required = false, defaultValue = "") String userName, Model model) { //opción SIN optional
            @RequestParam Optional <String> userName, Model model) { //opción con optional
            
            LocalDate fecha = LocalDate.now();
            
            model.addAttribute("date", fecha.getYear());
            // model.addAttribute("nombre", userName); //opción SIN optional
            model.addAttribute("nombre", userName.orElse("")); //opción con optional

        return "indexView";    // vista que devuelve
    }
    
}