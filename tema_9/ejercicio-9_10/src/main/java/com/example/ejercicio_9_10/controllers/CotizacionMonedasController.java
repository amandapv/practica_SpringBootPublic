package com.example.ejercicio_9_10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ejercicio_9_10.domain.FormConversion;
import com.example.ejercicio_9_10.services.CotizacionMonedasService;

@Controller
public class CotizacionMonedasController {
    
    @Autowired
    private CotizacionMonedasService cotizacionMonedasService;


    @GetMapping("/cotizacion")
    public String showDatosForm(Model model) {
        model.addAttribute("formConversion", new FormConversion());
        return "cotizacionMonedas";
    }

    //método para rellenar la información necesaria
    @PostMapping("/cotizacion/submit")
    public String showDatosFormSubmit(@ModelAttribute("formConversion") FormConversion formConversion, Model model) {
        System.out.println((formConversion));
        model.addAttribute("conversionMonedas", cotizacionMonedasService.cotizacionMonedas(formConversion));
        return "cotizacionMonedas";
    }
}
