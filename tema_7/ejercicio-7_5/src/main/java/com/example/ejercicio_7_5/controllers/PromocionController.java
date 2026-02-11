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
import com.example.ejercicio_7_5.domain.Promocion;
import com.example.ejercicio_7_5.services.CursoService;
import com.example.ejercicio_7_5.services.InfluencerService;
import com.example.ejercicio_7_5.services.PromocionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promocion")
public class PromocionController {
    
    @Autowired
    private PromocionService promocionService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private InfluencerService influencerService;

    private String txtMsg;

    @GetMapping("/")
    public String showList (Model model) {
        if (txtMsg != null ) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        model.addAttribute("listaPromociones", promocionService.obtenerTodos());
        return "/promocion/listView";
    }
    
    @GetMapping("/{id}")
    public String showElement(@PathVariable long id, Model model) {
        try {
            model.addAttribute("promocion", promocionService.obtenerPorId(id));
            if (txtMsg != null ) {
                model.addAttribute("msg", txtMsg);
                txtMsg = null;
            }
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "promocion/listOneView";
    }

    @GetMapping("/new/{idInfluencer}")
    public String showNew (@PathVariable Long idInfluencer, Model model) {
        try {
            model.addAttribute("promocionForm", new Promocion());
            model.addAttribute("listaCursos", cursoService.obtenerTodos());
            if (idInfluencer != null) {
                model.addAttribute("idInfluencer", idInfluencer);
            }
            if (txtMsg != null ) {
                model.addAttribute("msg", txtMsg);
                txtMsg = null;
            }
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "promocion/newFormView";
    }

    @PostMapping("/new/submit/{idInfluencer}")
    public String showNewSubmit (@PathVariable long idInfluencer, @Valid @ModelAttribute("promocionForm") Promocion promocionForm, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("listaCursos", cursoService.obtenerTodos());
                return "promocion/newFormView";
            }
            if (promocionForm.getCurso() != null && promocionForm.getCurso().getId() != null) {
                // Obtener el curso completo desde la BD usando el ID que viene del formulario
                Curso cursoCompleto = cursoService.obtenerPorId(promocionForm.getCurso().getId());
                promocionForm.setCurso(cursoCompleto);
                // Obtener el influencer completo desde la BD y asignarlo a la promoción
                Influencer influencerCompleto = influencerService.obtenerPorId(idInfluencer);
                promocionForm.setInfluencer(influencerCompleto);
                promocionService.añadir(promocionForm);
            } else {
                txtMsg = "Debe seleccionar un curso";
                model.addAttribute("msg", txtMsg);
                model.addAttribute("listaCursos", cursoService.obtenerTodos());
                return "promocion/newFormView";
            }
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "redirect:/influencer/" + idInfluencer;
    }
}
