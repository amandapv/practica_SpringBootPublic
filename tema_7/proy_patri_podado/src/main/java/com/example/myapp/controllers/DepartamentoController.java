package com.example.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myapp.domain.Departamento;
import com.example.myapp.services.DepartamentoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/depto") //Añadimos el prefijo depto a todas las rutas
public class DepartamentoController {

  /*Codigo aquí a escribir */

    private String txtMsg;

    // http://localhost:9000/depto/list
    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaDepartamentos",/*Codigo aquí a escribir */);
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "departamento/listView";
    }

    // http://localhost:9000/depto/new
    @GetMapping("/new")
    public String showNew(Model model) {
       /*Codigo aquí a escribir */
        return "departamento/newFormView";
    }

    // http://localhost:9000/depto/new/submit
    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Departamento departamentoForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
     /*Codigo aquí a escribir */
        try {
            /*Codigo aquí a escribir */
        }
        catch  (DataIntegrityViolationException e) {
            txtMsg = "Ya existe un departamento con ese nombre";
        }
        return "redirect:/depto/list";
    }


    // http://localhost:9000/depto/edit/1
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Departamento departamento = /*Codigo aquí a escribir */
        if (departamento != null) {
           /*Codigo aquí a escribir */
        } else {
            return "redirect:/depto/list";
        }
    }

    // http://localhost:9000/depto/edit/submit
    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid Departamento departamentoForm,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()){
           try {
             /*Codigo aquí a escribir */
            }
            catch  (DataIntegrityViolationException e) {
                txtMsg = "Ya existe un departamento con ese nombre";
            }
        }
        else txtMsg = "Error en formulario";
        return "redirect:/depto/list";
    }

    // http://localhost:9000/depto/delete/1
    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        /*Codigo aquí a escribir */
        return "redirect:/depto/list";
    }
}
