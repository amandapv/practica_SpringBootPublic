package com.example.myapp;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // anotación controlador
public class HomeController {
    
    @GetMapping({"/", "/home", "/app"})            // ruta a la que responde por GET
    public String showHome( 


            @RequestParam(required = false, defaultValue = "X") String userName, 
            Model model) {
            
            LocalDate fecha = LocalDate.now();
            
            model.addAttribute("date", fecha.getYear());
            model.addAttribute("nombre", userName);

        return "indexView";    // vista que devuelve
    }
    
}