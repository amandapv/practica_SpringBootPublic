package com.example.myapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;
import com.example.myapp.services.DepartamentoService;
import com.example.myapp.services.EmpleadoService;

import jakarta.validation.Valid;

@Controller
public class EmpleadoController {

    @Autowired
    public EmpleadoService empleadoService;

    //Puesto que un empleado tiene un departamento, también inyectamos el servicio de departamento
    @Autowired
    public DepartamentoService departamentoService;

    private String txtMsg;

    // http://localhost:9000/
    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaEmpleados", /*Codigo aquí a escribir */);
        model.addAttribute("listaDepartamentos", /*Codigo aquí a escribir */);
        model.addAttribute("deptoSeleccionado", 0); // new Departamento(0L, "Todos"));
        model.addAttribute("findForm",/*Codigo aquí a escribir */);
        // Si hay un mensaje de error, lo añadimos al modelo y lo eliminamos
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "empleado/listView";
    }

    // http://localhost:9000/1
    @GetMapping("/{id}")
    public String showElement(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("empleado", /*Codigo aquí a escribir */);
            return "empleado/listOneView";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
    }

    // http://localhost:9000/nuevo
    @GetMapping("/nuevo")
    public String showNew(Model model) {
        model.addAttribute("empleadoForm", new Empleado());
        model.addAttribute("listaDepartamentos", /*Codigo aquí a escribir */);
        return "empleado/newFormView";
    }

    // http://localhost:9000/nuevo/submit
    @PostMapping("/nuevo/submit")
    public String showNewSubmit(@Valid Empleado empleadoForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            txtMsg = "Error en formulario";
            return "redirect:/";
        }
        try {
            empleadoService.añadir(empleadoForm);
            txtMsg = "Operación realizada con éxito";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
        }
        return "redirect:/";
    }

    // http://localhost:9000/editar/1
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        try {
            Empleado empleado = empleadoService.obtenerPorId(id);
            model.addAttribute("empleadoForm", empleado);
            model.addAttribute("listaDepartamentos", /*Codigo aquí a escribir */);
            return "empleado/editFormView";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
    }

    // http://localhost:9000/editar/1/submit
    @PostMapping("/editar/{id}/submit")
    public String showEditSubmit(@PathVariable Long id, @Valid Empleado empleadoForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            txtMsg = "Error en formulario";
            return "redirect:/";
        }
        try {
           /*Codigo aquí a escribir */
            txtMsg = "Operación realizada con éxito";
        } catch (RuntimeException e) {
            txtMsg = e.getMessage();
        }
        return "redirect:/";
    }

    // http://localhost:9000/borrar/1
    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
       /*Codigo aquí a escribir */
        txtMsg = "Operación realizada con éxito";
        return "redirect:/";
    }

    // http://localhost:9000/findByName
    @PostMapping("/findByName")
    public String showFindByNameSubmit(Empleado empleadoForm, Model model) {
        model.addAttribute("listaEmpleados", /*Codigo aquí a escribir */);
        model.addAttribute("listaDepartamentos", /*Codigo aquí a escribir */);
        model.addAttribute("findForm", empleadoForm);
        return "empleado/listView";
    }

    // http://localhost:9000/findByGenero/MASCULINO
    @GetMapping("/findByGenero/{genero}")
    public String showFindByGenero(@PathVariable Genero genero, Model model) {
        model.addAttribute("listaEmpleados", /*Codigo aquí a escribir */);
        model.addAttribute("listaDepartamentos", /*Codigo aquí a escribir */);
        model.addAttribute("findForm", new Empleado());
        model.addAttribute("generoSeleccionado", genero);
        return "empleado/listView";
    }

    // http://localhost:9000/porDepto/1
    @GetMapping("/porDepto/{idDepto}")
    public String showbyDepto(@PathVariable Long idDepto, Model model) {
        model.addAttribute("listaEmpleados",/*Codigo aquí a escribir */);
        model.addAttribute("listaDepartamentos", /*Codigo aquí a escribir */);
        model.addAttribute("deptoSeleccionado", idDepto); 
        model.addAttribute("findForm", /*Codigo aquí a escribir */);
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "empleado/listView";
    }

    // http://localhost:9000/listado1/20000
    @GetMapping("/listado1/{salario}")
    public String showListado1(@PathVariable Double salario, Model model) {
        List<Empleado> empleados = /*Codigo aquí a escribir */
        model.addAttribute("tituloListado", "Empleados salario mayor que " +
                salario.toString());
        model.addAttribute("listaEmpleados", empleados);
        return "empleado/listadosView";
    }

    // http://localhost:9000/listado2
    @GetMapping("/listado2")
    public String showListado2(Model model) {
        List<Empleado> empleados = /*Codigo aquí a escribir */
        model.addAttribute("tituloListado", "Empleados salario mayor que la media");
        model.addAttribute("listaEmpleados", empleados);
        return "empleado/listadosView";
    }

}
