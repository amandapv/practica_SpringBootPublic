package com.example.calculos_matematicos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.calculos_matematicos.services.CalculosService;

@Controller
@RequestMapping("/calculos")

public class CalculosController {
    @Autowired
    private CalculosService calcularServicio;

    @GetMapping({ "/home" })
    public String showHome() {
        return "indexView";
    }


    // @GetMapping({ "/errores" }) //otra opción sería utilizar un getMapping al que después iríamos en el html para mostrar los errores, pero en este caso solo haré una función
    public String showErrores(Model model, String txtError) {

        if (txtError != null) {
            model.addAttribute("txtErr", txtError);
            txtError = null; // vacía la variable para usarla de nuevo
        }
        return "errorView";
    }


    @GetMapping("/primo") //pasar los parámetros en la parte de la query, después del path. Para rescatar los valores proporcionados en la parte query usaremos @RequestParam
    public String showPrimo(
        @RequestParam (required = false) String numCalcPrimo,
        Model model) {

        try {
            model.addAttribute("numeroPrimo", numCalcPrimo);
            model.addAttribute("resultado", calcularServicio.calcularPrimo(numCalcPrimo));
            return "resultadoPrimoView";
        } catch (RuntimeException ex) {
            showErrores(model, ex.getMessage());
            return "errorView";
        }
    }


    @GetMapping("/calcularHipotenusa/{cat1}/{cat2}") //pasar los parámetros en el "path" de la URL. Para rescatar los valores proporcionados en el path usaremos @PathVariable
    public String showHipot(@PathVariable String cat1, @PathVariable String cat2, Model model) {

        try {
            model.addAttribute("resultado", calcularServicio.calcularHipotenusa(cat1, cat2));
            return "resultadoHipotenusaView";
        }
        catch (RuntimeException ex) {
            showErrores(model, ex.getMessage());
            return "errorView";
        }
    }


    @GetMapping("/divisores/{numDiv}")
    public String showDivisores(@PathVariable String numDiv, Model model) {

        try {
            model.addAttribute("numeroDiv", numDiv);
            model.addAttribute("listadoDivisores", calcularServicio.calcularDivisores(numDiv));
        } catch (Exception ex) {
            showErrores(model, ex.getMessage());
            return "errorView";
        }
        return "resultadoDivisores";
    }

}
