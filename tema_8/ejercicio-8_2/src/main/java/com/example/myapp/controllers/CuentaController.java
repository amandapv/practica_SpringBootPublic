package com.example.myapp.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myapp.domain.Cuenta;
import com.example.myapp.services.CuentaService;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    public CuentaService cuentaService;

    @GetMapping({ "/", "" })
    public String showList(Model model) {
        model.addAttribute("listaCuentas", cuentaService.obtenerTodos());
        return "account/accountListView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("cuentaForm", new Cuenta());
        return "account/accountNewView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(
            @Valid @ModelAttribute("cuentaForm") Cuenta nuevaCuenta,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors())
            cuentaService.añadir(nuevaCuenta);
        return "redirect:/cuentas/";
    }

    @GetMapping("/edit/{iban}")
    public String showEditForm(@PathVariable String iban, Model model) {
        Cuenta cuenta = cuentaService.obtenerPorId(iban);
        if (cuenta != null) {
            model.addAttribute("cuentaForm", cuenta);
            return "account/accountEditView";
        } else {
            return "redirect:/cuentas/";
        }
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("cuentaForm") Cuenta cuenta,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) 
            cuentaService.editar(cuenta);
        return "redirect:/cuentas/";
        
    }

    @GetMapping("/delete/{iban}")
    public String showDelete(@PathVariable String iban) {
        cuentaService.borrar(iban);
        return "redirect:/cuentas/";
    }
}
