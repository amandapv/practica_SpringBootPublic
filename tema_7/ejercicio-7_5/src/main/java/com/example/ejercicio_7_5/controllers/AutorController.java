package com.example.ejercicio_7_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ejercicio_7_5.domain.Autor;
import com.example.ejercicio_7_5.repositories.AutorRepository;
import com.example.ejercicio_7_5.services.AutorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/autor")
public class AutorController {

    private final AutorRepository autorRepository;
    
    @Autowired
    private AutorService autorService;

    private String txtMsg;

    AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping({ "/", "/list", "" })
    public String showList(Model model) {
        model.addAttribute("listaAutores", autorService.obtenerTodos());
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "autor/listView";
    }

    //PODRÍA CREAR EL CONTROLADOR Y LA VISTA PARA LA VISUALIZACIÓN DE UN AUTOR EN CONCRETO PERO POR AHORA NO LO HARÉ

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("autorForm", new Autor());
        return "autor/newFormView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid @ModelAttribute("autorForm") Autor autorForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "autor/newFormView";
        }
        try {
            autorService.añadir(autorForm);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
           txtMsg = e.getMessage();
           return "redirect:/autor/list";
        }
        return "redirect:/autor/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        try {
            // Autor autor = autorService.editar(id);
            model.addAttribute("autorForm", autorService.obtenerPorId(id)); //le pasamos el ID del autor a editar
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/autor/";
        }
        return "autor/editFormView";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("autorForm") Autor autorForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editFormView";
        }
        try {
            autorService.editar(autorForm);
            txtMsg = "Operación realizada con éxito";            
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/autor/list";
        }
        return "redirect:/autor/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        try {
            autorService.borrar(id);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/autor/list";
        }
       
        return "redirect:/autor/list";
    }

}
