package com.example.ejercicio_7_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ejercicio_7_1.domain.Curso;
import com.example.ejercicio_7_1.domain.Tematica;
import com.example.ejercicio_7_1.services.CursoService;
import com.example.ejercicio_7_1.services.CursoServiceImpl;

import jakarta.validation.Valid;

@Controller
public class CursoController {

    private final CursoServiceImpl cursoServiceImpl;

    @Autowired
    public CursoService cursoService;

    private String txtMsg;

    CursoController(CursoServiceImpl cursoServiceImpl) {
        this.cursoServiceImpl = cursoServiceImpl;
    }

    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaCursos", cursoService.obtenerTodos());
        model.addAttribute("cursoForm", new Curso()); //hay que añadirle el curso porque en el archivo listView lo requiere para el filtro de la busqueda
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "listView";
    }

    @GetMapping("/{id}")
    public String showElement(@PathVariable Long id, Model model) {
        try {
            Curso curso = cursoService.obtenerPorId(id);
            model.addAttribute("curso", curso);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "listOneView";
    }

    @GetMapping("/nuevo")
    public String showNew(Model model) {
        // el commandobject del formulario es una instancia de curso vacia
        model.addAttribute("cursoForm", new Curso());
        return "newFormView";
    }

    @PostMapping("/nuevo/submit")
    public String showNewSubmit(@Valid @ModelAttribute("cursoForm") Curso cursoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("cursoForm", cursoForm); // No hace falta añadir al model si el nombre coincide con @ModelAttribute
            return "newFormView";
        }

        try {
            cursoService.añadir(cursoForm);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        try {
            Curso curso = cursoService.obtenerPorId(id);
            model.addAttribute("cursoForm", curso);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "editFormView";
    }

    @PostMapping("/editar/{id}/submit")
    public String showEditSubmit(@PathVariable Long id, @Valid @ModelAttribute("cursoForm") Curso cursoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("cursoForm", cursoForm); // No hace falta añadir al model si el nombre coincide con @ModelAttribute
            return "editFormView";
        }
        try {
            cursoService.editar(cursoForm);
            txtMsg = "Operación realizada con éxito";            
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        try {
            cursoService.borrar(id);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "redirect:/";
    }

    @PostMapping("/findByName")
    public String showFindByName(@ModelAttribute("cursoForm") Curso cursoForm, Model model) {
        model.addAttribute("listaCursos", cursoService.buscarPorNombre(cursoForm.getNombre()));
        return "listView";
    }


    @GetMapping("/findByTematica/{tematica}")
    public String showFindByTematica(@PathVariable Tematica tematica, Model model) {

        model.addAttribute("listaCursos", cursoServiceImpl.buscarPorTematica(tematica));
        model.addAttribute("tematicaSeleccionada", tematica);
        model.addAttribute("cursoForm", new Curso());
        return "listView";
    }
}
