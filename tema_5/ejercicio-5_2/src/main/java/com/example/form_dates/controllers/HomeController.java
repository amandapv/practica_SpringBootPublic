package com.example.form_dates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form_dates.Formulario;
import com.example.form_dates.interfaces.CalcFechaService;


@Controller // anotación controlador
public class HomeController {

    @Autowired
    private CalcFechaService calcFechaService;
    
    @GetMapping({"/", "/form"})            // ruta a la que responde por GET
    public String showHome(Model model) {
        model.addAttribute("form", new Formulario()); //creamos un objeto de la clase formulario y lo añadimos al modelo
        return "formView";    // vista que devuelve
    }

    @PostMapping("/form/submit")
    public String postMethodName(Formulario form, Model model) {
        // Integer suma = form.getNumero1() + form.getNumero2(); //podría también haber metido la suma en el Formulario
        // model.addAttribute("form", form); //subir el formulario actualizado con los números
        // model.addAttribute("suma", suma); //subir el resultado de la suma
        return "formSubmitView";
    }
    

}