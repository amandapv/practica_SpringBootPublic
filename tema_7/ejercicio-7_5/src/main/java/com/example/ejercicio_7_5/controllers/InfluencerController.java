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

import com.example.ejercicio_7_5.domain.Curso;
import com.example.ejercicio_7_5.domain.Influencer;
import com.example.ejercicio_7_5.services.CursoService;
import com.example.ejercicio_7_5.services.InfluencerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/influencer")

public class InfluencerController {
    
    @Autowired
    private InfluencerService influencerService;

    @Autowired
    private CursoService cursoService;

    private String txtMsg;

    @GetMapping({"/", "/list", ""}) //mostrar todos los influencers
    public String showList(Model model) {
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        model.addAttribute("listaInfluencers", influencerService.obtenerTodos());
        return "influencer/listView";
    }

    @GetMapping("/{id}") //mostrar un influencer
    public String showElement(@PathVariable long id, Model model) {
        try {
            Influencer influencer = influencerService.obtenerPorId(id);
            model.addAttribute("influencer", influencer);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/influencer/list";
        }
        return "influencer/listOneView";
    }

    @GetMapping("/new") //añadir un influencer
    public String showNew(Model model) {
        try {
            model.addAttribute("influencerForm", new Influencer());
            model.addAttribute("listaCursos", cursoService.obtenerTodos()); //para decirle el curso al que lleva el influencer
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/influencer/list";
        }
        return "influencer/newFormView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid @ModelAttribute("influencerForm") Influencer influencerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "influencer/newFormView";
        }
        try {
            influencerService.añadir(influencerForm);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/influencer/list";
        }
        return "redirect:/influencer/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        try {
            Influencer influencer = influencerService.obtenerPorId(id);
            model.addAttribute("influencerForm", influencer);
            model.addAttribute("listaCursos", cursoService.obtenerTodos()); //para decirle el curso al que lleva el influencer
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/influencer/list";
        }
        return "influencer/editFormView";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("influencerForm") Influencer influencerForm, Curso curso, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "influencer/editFormView";
        }
        try {
            if (curso != null) {
                //crear promocion con el ID de influencer + ID curso
            }
            influencerService.editar(influencerForm);
            txtMsg = "Operación realizada con éxito";            
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/influencer/list";
        }
        return "redirect:/influencer/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        try {
            influencerService.borrar(id);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/influencer/list";
        }
        return "redirect:/influencer/list";
    }

}
