package com.example.ejercicio_7_4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ejercicio_7_4.domain.Autor;
import com.example.ejercicio_7_4.domain.Curso;
import com.example.ejercicio_7_4.domain.Tematica;
import com.example.ejercicio_7_4.services.AutorService;
import com.example.ejercicio_7_4.services.CursoService;

import jakarta.validation.Valid;

@Controller
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired 
    private AutorService autorService;    

    private String txtMsg;

    CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaCursos", cursoService.obtenerTodos());
        model.addAttribute("cursoForm", new Curso()); //hay que añadirle el curso porque en el archivo listView lo requiere para el filtro de la busqueda
        model.addAttribute("listaAutores", autorService.obtenerTodos());
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
        model.addAttribute("listaAutores", autorService.obtenerTodos());
        return "newFormView";
    }

    @PostMapping("/nuevo/submit")
    public String showNewSubmit(@Valid @ModelAttribute("cursoForm") Curso cursoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("cursoForm", cursoForm); // No hace falta añadir al model si el nombre coincide con @ModelAttribute
            model.addAttribute("listaAutores", autorService.obtenerTodos()); // Si hay errores, hay que volver a cargar la lista de autores para el select. Tengo que traerlos de vuelta (a diferencia de la Tematica) porque está en grabada en el ccódigo Java y los autores son registros de una BBDD
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
            model.addAttribute("listaAutores", autorService.obtenerTodos());
            // model.addAttribute("autorSeleccionado", autorService.obtenerPorId(id));
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/";
        }
        return "editFormView";
    }

    @PostMapping("/editar/submit") //no necesito pasarle el ID por path
    public String showEditSubmit(@Valid @ModelAttribute("cursoForm") Curso cursoForm, BindingResult bindingResult) {
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

        model.addAttribute("listaCursos", cursoService.buscarPorTematica(tematica));
        model.addAttribute("tematicaSeleccionada", tematica);
        model.addAttribute("cursoForm", new Curso());
        model.addAttribute("listaAutores", autorService.obtenerTodos()); //cargamos la lista de autores para que el <select> siga funcionando. Es decir Lista COMPLETA de autores para el select
        return "listView";
    }

    @PostMapping("/findByPrecioLessThan")
    public String showFindByPrecioLessThan(@ModelAttribute("cursoForm") Curso curso, Model model) {
        model.addAttribute("listaCursos", cursoService.filtrarImporteMenorIgualPrecio(curso.getPrecio()));
        return "listView";
    }

    @GetMapping("/findByAutor/{idAutor}")
    public String showFindByAutor(@PathVariable Long idAutor, Model model) {
        model.addAttribute("listaCursos", cursoService.buscarPorAutorId(idAutor)); //Cursos FILTRADOS para la tabla por autor
        model.addAttribute("cursoForm", new Curso()); //Cargamos el objeto que pide el formulario en listView.html
        model.addAttribute("listaAutores", autorService.obtenerTodos()); //cargamos la lista de autores para que el <select> siga funcionando. Es decir Lista COMPLETA de autores para el select
        model.addAttribute("idAutorSeleccionado", idAutor); //Paso el ID seleccionado para que el select se quede marcado
        return "listView";
    }
}