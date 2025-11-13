package com.example.crud_basico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.crud_basico.domain.Curso;
import com.example.crud_basico.services.CursoService;

import jakarta.validation.Valid;

@Controller
public class CursoController {

    @Autowired
    public CursoService cursoService;

    private String txtMsg;

    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaCursos", cursoService.obtenerTodos());
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "listView";
    }

    @GetMapping("/{id}")
    public String showElement(@PathVariable Long id, Model model) {
        Curso curso = cursoService.obtenerPorId(id);
        if (curso == null) {
            txtMsg = "Curso no encontrado";
            return "redirect:/";
        }
        model.addAttribute("curso", curso);
        return "listOneView";

    }

    @GetMapping("/nuevo")
    public String showNew(Model model) {
        // el commandobject del formulario es una instancia de curso vacia
        model.addAttribute("cursoForm", new Curso());
        return "newFormView";
    }

    @PostMapping("/nuevo/submit")
    public String showNewSubmit(@Valid Curso cursoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            txtMsg = "Error en formulario";
            return "redirect:/";
        }
        cursoService.añadir(cursoForm);
        txtMsg = "Operación realizada con éxito";
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Curso curso = cursoService.obtenerPorId(id);
        if (curso == null) {
            txtMsg = "Curso no encontrado";
            return "redirect:/";
        }
        model.addAttribute("cursoForm", curso);
        return "editFormView";
    }

    @PostMapping("/editar/{id}/submit")
    public String showEditSubmit(@PathVariable Long id, @Valid Curso cursoForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            txtMsg = "Error en formulario";
            return "redirect:/";
        }
        Curso curso = cursoService.editar(cursoForm);
        if (curso == null)
            txtMsg = "Curso no encontrado";
        else
            txtMsg = "Operación realizada con éxito";
        return "redirect:/";
    }

    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        cursoService.borrar(id);
        txtMsg = "Operación realizada con éxito";
        return "redirect:/";
    }
}
