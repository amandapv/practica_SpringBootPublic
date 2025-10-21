package com.example.simple_form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller // anotación controlador
public class HomeController {

    @GetMapping({"/", "/form"})            // ruta a la que responde por GET
    public String showHome(Model model) {
        model.addAttribute("form", new Formulario()); //creamos un objeto de la clase formulario y lo añadimos al modelo
        return "formView";    // vista que devuelve
    }

    @PostMapping("/form/submit")
    public String postMethodName(Formulario form, Model model) {
        Integer suma = form.getNumero1() + form.getNumero2(); //podría también haber metido la suma en el Formulario
        model.addAttribute("form", form); //subir el formulario actualizado con los números
        model.addAttribute("suma", suma); //subir el resultado de la suma
        return "formSubmitView";
    }
    
    //esta es una opción en la que la suma ya se realiza en la clase Formulario, por lo que solo hay que hacer un getter del método Suma  
    // @PostMapping("/form/submit")  
    // public String postMethodName(Formulario form, Model model) {
    //     model.addAttribute("form", form); //subir el formulario actualizado con los números
    //     return "formSubmitView";
    // }
}