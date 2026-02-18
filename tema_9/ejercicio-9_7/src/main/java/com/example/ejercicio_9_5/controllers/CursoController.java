package com.example.ejercicio_9_5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio_9_5.domain.Curso;
import com.example.ejercicio_9_5.domain.Tematica;
import com.example.ejercicio_9_5.services.CursoService;

import jakarta.validation.Valid;

@RestController
public class CursoController {

    @Autowired
    public CursoService cursoService;

    @GetMapping("/curso")
    public List<Curso> getList() {
        return cursoService.obtenerTodos();
    }

    @GetMapping("/curso/{id}")
    public Curso getOneElement(@PathVariable Long id) {
        return cursoService.obtenerPorId(id);
    }

    @PostMapping("/curso")
    public ResponseEntity<?> newElement(@Valid @RequestBody Curso nuevoCurso) {
        //@Valid si no se cumple la validación devuelve BAD_REQUEST y devuelve el código 400
        Curso curso = cursoService.añadir(nuevoCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso); //como no quiero devolver un código 200 (que es el que se devuelve por defecto) y quiero enviar un 201 se lo tengo que especificar
    }

    @PutMapping("/curso/{id}")
    public Curso editElement(@PathVariable Long id, @Valid @RequestBody Curso editCurso) {
        //@Valid si no se cumple la validación devuelve BAD_REQUEST y devuelve el código 400
        cursoService.obtenerPorId(id); //ver si el id que me pasa existe y sino lanzará una excepción
        return cursoService.editar(editCurso);
    }

    @DeleteMapping("/curso/{id}")
    public ResponseEntity<?> editElement(@PathVariable long id) {
        cursoService.obtenerPorId(id);
        cursoService.borrar(id);
        return ResponseEntity.noContent().build(); //devuelve el código 204 (encontró el elemento a borrar) 
    }

    @GetMapping("/findByName")
    public List<Curso> showFindByName(@RequestParam String nombre) {
        return cursoService.buscarPorNombre(nombre);
    }

    @GetMapping("/findByTematica/{tematica}")
    public List<Curso> showFindByTematica(@PathVariable Tematica tematica) {
        return cursoService.buscarPorTematica(tematica);
    }

    @GetMapping("/findByPrecioLessThan")
    public List<Curso> showFindByPrecioLessThan(@RequestParam Double precio) {
        return cursoService.filtrarImporteMenorIgualPrecio(precio);
    }
}