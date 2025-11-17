package com.example.crud_basico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private String txtAciertos;
    // private String txtValid;

    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaCursos", cursoService.obtenerTodos());
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        if (txtAciertos != null) {
            model.addAttribute("aciertos", txtAciertos);
            txtAciertos = null;
        }
        return "listView";
    }

    @GetMapping("/{id}")
    public String showElement(@PathVariable Long id, Model model) {
        try {
            Curso curso = cursoService.obtenerPorId(id);
            model.addAttribute("curso", curso);
            return "listOneView";
            
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
    }

    @GetMapping("/nuevo")
    public String showNew(Model model) {
        // el commandobject del formulario es una instancia de curso vacia
        model.addAttribute("cursoForm", new Curso());
        return "newFormView";
    }

    @PostMapping("/nuevo/submit")
    public String showNewSubmit(@Valid @ModelAttribute("cursoForm")  Curso cursoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cursoForm", cursoForm);
            return "newFormView";
        }

        try {
            cursoService.añadir(cursoForm);
            txtAciertos = "Operación realizada con éxito";

        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
        }

        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        try {
            Curso curso = cursoService.obtenerPorId(id);
            model.addAttribute("cursoForm", curso);
            return "editFormView";

        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
    }

    @PostMapping("/editar/{id}/submit")
    public String showEditSubmit(@PathVariable Long id, @Valid @ModelAttribute("cursoForm") Curso cursoForm, BindingResult bindingResult, Model model) { //Si omito la anotación @ModelAttribute("cursoForm"), los errores de validación (BindingResult) no se muestran porque Spring no puede encontrar el objeto al que debe adjuntar esos errores cuando vuelve a cargar la vista
        if (bindingResult.hasErrors()) {
            model.addAttribute("cursoForm", cursoForm); //en este caso se podría quitar porque redirijo a ese html pero por si en algún momento se cambiase el redirect y también por buenas prácticas, lo dejaré
            return "editFormView";
        }
        try {
            cursoService.editar(cursoForm);
            txtAciertos = "Operación realizada con éxito";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        try {
            cursoService.borrar(id);
            txtAciertos = "Operación realizada con éxito";

        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
        }
        return "redirect:/";
    }
}
