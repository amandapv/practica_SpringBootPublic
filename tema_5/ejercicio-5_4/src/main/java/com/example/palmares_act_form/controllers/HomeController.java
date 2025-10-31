package com.example.palmares_act_form.controllers;

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
            @RequestParam Optional <String> name, Model model) { //opción con optional
            
            LocalDate fecha = LocalDate.now();
            
            model.addAttribute("date", fecha.getYear());
            // model.addAttribute("nombre", name); //opción SIN optional
            model.addAttribute("nombre", name.orElse("")); //opción con optional

        return "indexView";    // vista que devuelve
    }
    
}