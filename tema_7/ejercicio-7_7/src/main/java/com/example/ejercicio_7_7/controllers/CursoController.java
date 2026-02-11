package com.example.ejercicio_7_7.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ejercicio_7_7.domain.Curso;
import com.example.ejercicio_7_7.domain.Tematica;
import com.example.ejercicio_7_7.services.AutorService;
import com.example.ejercicio_7_7.services.CursoService;

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
        List<Curso> listaCursos = cursoService.obtenerTodos(); //obtenga la lista de cursos (de la entidad)...
        model.addAttribute("listaCursos", cursoService.convertCursoToDto(listaCursos)); //... y convierto ese listado de cursos anterior en un listado de cursos de la clase DTO (es decir con los campos de esta nueva clase) y no de la entidad

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
        List<Curso> listaCursos = cursoService.buscarPorNombre(cursoForm.getNombre()); //guardo en esta lista los cursos que encuentra por ese nombre
        model.addAttribute("listaCursos", cursoService.convertCursoToDto(listaCursos)); //y convierto esa lista al DTO
        return "listView";
    }


    @GetMapping("/findByTematica/{tematica}")
    public String showFindByTematica(@PathVariable Tematica tematica, Model model) {
        List<Curso> listaCursos = cursoService.buscarPorTematica(tematica);
        model.addAttribute("listaCursos", cursoService.convertCursoToDto(listaCursos));
        model.addAttribute("tematicaSeleccionada", tematica);
        model.addAttribute("cursoForm", new Curso());
        return "listView";
    }


    @PostMapping("/findByPrecioLessThan")
    public String showFindByPrecioLessThan(@ModelAttribute("cursoForm") Curso curso, Model model) {
        List<Curso> listaCursos = cursoService.filtrarImporteMenorIgualPrecio(curso.getPrecio());
        model.addAttribute("listaCursos", cursoService.convertCursoToDto(listaCursos));
        return "listView";
    }
}