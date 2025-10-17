package com.example.calculo_hipotenusa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.calculo_hipotenusa.services.CalcHipoService;

@Controller
public class CalcHipoController {

    @Autowired
    private CalcHipoService calculoHipoService;
    private String txtError;

    @GetMapping({ "/", "/home" })
    public String showHome(Model model) {
        
        if (txtError != null) {
            model.addAttribute("txtErr", txtError);
            txtError = null; // vacía la variable para usarla de nuevo
        }
        return "indexView";
    }


    @GetMapping("/calcularHipotenusa/{cat1}/{cat2}")
    public String showHipot(@PathVariable String cat1, @PathVariable String cat2, Model model) {
        try {
            model.addAttribute("resultado", calculoHipoService.calcularHipotenusa(cat1, cat2));
            return "resultadoView";
        }
        catch (RuntimeException ex) {
            txtError = ex.getMessage();
            return "redirect:/home";
        }
    }

}
