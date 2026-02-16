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
import com.example.myapp.domain.Movimiento;
import com.example.myapp.services.CuentaService;
import com.example.myapp.services.MovimientoService;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    public MovimientoService movimientoService;
    @Autowired
    public CuentaService cuentaService;

    @GetMapping("/")
    public String showList(Model model) {
        model.addAttribute("listaMovimientos", movimientoService.obtenerTodos());
        return "movement/movementListView";
    }

    @GetMapping("/{iban}")
    public String showMovCuenta(@PathVariable String iban, Model model) {
        Cuenta cuenta = cuentaService.obtenerPorId(iban);
        model.addAttribute("listaMovimientos", movimientoService.obtenerPorCuenta(cuenta));
        return "movement/movementListView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("movimientoForm", new Movimiento());
        model.addAttribute("listaCuentas", cuentaService.obtenerTodos());
        return "movement/movementNewView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(
            @Valid @ModelAttribute("movimientoForm") Movimiento nuevoMovimiento,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean movimientoOk = movimientoService.añadir(nuevoMovimiento);
            if (movimientoOk)
	        	cuentaService.actualizarSaldo(nuevoMovimiento);
	    }
        return "redirect:/cuentas/";
    }

}
