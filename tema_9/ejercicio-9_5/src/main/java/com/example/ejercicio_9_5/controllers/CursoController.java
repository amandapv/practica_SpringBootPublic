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
import org.springframework.web.server.ResponseStatusException;

import com.example.ejercicio_9_5.domain.Curso;
import com.example.ejercicio_9_5.domain.Tematica;
import com.example.ejercicio_9_5.excepciones.CursoNotFoundException;
import com.example.ejercicio_9_5.excepciones.EmptyCursosException;
import com.example.ejercicio_9_5.services.CursoService;

import jakarta.validation.Valid;

@RestController
public class CursoController {

    @Autowired
    public CursoService cursoService;

    @GetMapping("/curso")
    public ResponseEntity<?> getList() {
        List<Curso> listaCursos; 
        try {
            listaCursos = cursoService.obtenerTodos();
        } catch (EmptyCursosException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        // return ResposeEntity.status(HttpStatus.OK).body(listaCursos);  //en vez de hacerlo como abajo también se podría hacer así
        return ResponseEntity.ok(listaCursos); //devuelve el código 200 (OK)
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<?> getOneElement(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cursoService.obtenerPorId(id));
        } catch (CursoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/curso")
    public ResponseEntity<?> newElement(@Valid @RequestBody Curso nuevoCurso) {
        //@Valid si no se cumple la validación devuelve BAD_REQUEST y devuelve el código 400
        Curso curso = cursoService.añadir(nuevoCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @PutMapping("/curso/{id}")
    public ResponseEntity<?> editElement(@PathVariable Long id, @Valid @RequestBody Curso editCurso) {
        //@Valid si no se cumple la validación devuelve BAD_REQUEST y devuelve el código 400
        try {
            Curso curso = cursoService.editar(editCurso);
            return ResponseEntity.ok(curso);
        } catch (CursoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/curso/{id}")
    public ResponseEntity<?> editElement(@PathVariable long id) {
        try {
            cursoService.borrar(id);
            return ResponseEntity.noContent().build(); //devuelve el código 204 (encontró el elemento a borrar) 
        } catch (CursoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> showFindByName(@RequestParam String nombre) {
        try {
            return ResponseEntity.ok(cursoService.buscarPorNombre(nombre));
        } catch (EmptyCursosException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/findByTematica/{tematica}")
    public ResponseEntity<?> showFindByTematica(@PathVariable Tematica tematica) {
        try {
            return ResponseEntity.ok(cursoService.buscarPorTematica(tematica));
        } catch (EmptyCursosException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/findByPrecioLessThan")
    public ResponseEntity<?> showFindByPrecioLessThan(@RequestParam Double precio) {
        try {
            return ResponseEntity.ok(cursoService.filtrarImporteMenorIgualPrecio(precio));
        } catch (EmptyCursosException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}