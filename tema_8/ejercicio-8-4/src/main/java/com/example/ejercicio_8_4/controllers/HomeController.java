package com.example.ejercicio_8_4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ejercicio_8_4.domain.Voto;
import com.example.ejercicio_8_4.interfaces.VotacionService;

import jakarta.validation.Valid;

@Controller // anotación controlador
public class HomeController {
    
    @Autowired
    private VotacionService votacionService;
    private Voto form = new Voto(); //la inicializo de forma global para que en el getMapping no tenga que crearlo ahí y no se resetee
    private String txtError;


    @GetMapping({"/", "/home"})            // ruta a la que responde por GET
    public String showHome(Model model) {

        if (txtError != null) {
            model.addAttribute("txtErr", txtError);
            txtError = null; // vacía la variable para usarla de nuevo
        }
        
        model.addAttribute("form", form); //añado el objeto form al modelo
        model.addAttribute("listadoVoto", votacionService.getListadoVoto()); //le paso a la vista el listado de votos
        model.addAttribute("listadoEmail", votacionService.getListadoEmail()); //le paso a la vista el listado de emails

        return "indexView";    // vista que devuelve
    }


    @PostMapping("/voto")  
    public String votacion(@Valid @ModelAttribute("form") Voto form, BindingResult bindingresult, Model model) {
        
        //si hay errores en la validación..
        if (bindingresult.hasErrors()) {
            model.addAttribute("form", form);
            model.addAttribute("listadoVoto", votacionService.getListadoVoto()); //le paso a la vista el listado de votos
            model.addAttribute("listadoEmail", votacionService.getListadoEmail()); //
            model.addAttribute("txtErr", txtError);
            // System.out.println(bindingresult.toString());  

            return "indexView"; 
        }

        try {
            votacionService.votacion(form); //llamo al método de la votación del servicio
            //NO SIRVE DE NADA QUE AÑADA AL MODELO ESTA INFORMACIÓN PORQUE COMO REDIRIJO A / LA VARIABLE MODEL SE RESETEA YA QUE ES LOCAL AL MÉTODO VOTACION ---------------------------------------------
            // model.addAttribute("form", form);
            // model.addAttribute("listadoVoto", votacionService.getListadoVoto()); //le paso a la vista el listado de votos
            // model.addAttribute("listadoEmail", votacionService.getListadoEmail()); //le paso a la vista el listado de emails

            System.out.println(votacionService.getListadoVoto());  
        }
        catch (Exception ex) {
            txtError = ex.getMessage();
            return "redirect:/";
        }

        return "redirect:/";
    }
    
}