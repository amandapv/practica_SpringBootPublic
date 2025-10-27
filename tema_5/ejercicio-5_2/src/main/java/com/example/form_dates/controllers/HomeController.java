package com.example.form_dates.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.form_dates.Formulario;
import com.example.form_dates.interfaces.CalcFechaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller // anotación controlador
public class HomeController {

    @Autowired
    private CalcFechaService calcFechaService;
    private String txtError;
    
    @GetMapping({"/", "/form"})            // ruta a la que responde por GET
    public String showHome(Model model) {

        if (txtError != null) {
            model.addAttribute("txtErr", txtError);
            txtError = null; // vacía la variable para usarla de nuevo
        }

        model.addAttribute("form", new Formulario()); //creamos un objeto de la clase formulario y lo añadimos al modelo
        model.addAttribute("calcDias",0);
        model.addAttribute("calcBisiestos", null);
        model.addAttribute("calc11Enero", null);

        return "formView";    // vista que devuelve
    }


    @PostMapping("/")
    public String postMethodName(@Valid @ModelAttribute("form") Formulario form, BindingResult bindingResult, String valorBoton, Model model) { //añadir @valid al objeto form + BindingResult para validar el formulario (éste debe ir justo después del @valid con el objeto de la clase, en este caso el Form) + @ModelAttribute("form") le dice a Spring que el objeto se debe llamar "form" en el modelo y así permite que TH encuentre el objeto
        // hay errores en la validación...
        if (bindingResult.hasErrors()) {
            model.addAttribute("form", form); //añado el objeto form al modelo cuando hay errores
            return "formView"; 
        }

        //intentamos ejecutar las llamadas a los métodos de la interfaz que implementa el servicio para ver si la fecha 1 es anterior a la fecha 2 
        try {
            calcFechaService.verificarFechas(form); //llamo al método para verificar que las fechas están bien introducidas
        } //si la fecha 1 NO es anterior a la fecha 2, almacenará el error en esta variable y lanzará un RuntimeException
        catch (RuntimeException ex) {
            txtError = ex.getMessage();
            return "redirect:/";
        }

        if ("Calcular dias".equals(valorBoton)) { //si coincide el texto que pongo con el valor del botón que se está pulsando...
            model.addAttribute("calcDias", calcFechaService.calcularDias(form));
        } 
        if ("Calcular bisiestos".equals(valorBoton)) {
            model.addAttribute("calcBisiestos", calcFechaService.calcularBisiestos(form));
            // System.out.println(calcFechaService.calcularBisiestos(form));
        } 
        if ("Calcular domingos".equals(valorBoton)) {
            model.addAttribute("calc11Enero", calcFechaService.calcularAnios11Domingo(form));
        }

        model.addAttribute("form", form);
        return "formView";    // vista que devuelve
        
    }
    

}