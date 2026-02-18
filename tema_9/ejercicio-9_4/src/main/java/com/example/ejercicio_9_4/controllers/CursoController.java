package com.example.ejercicio_9_4.controllers;

import java.util.ArrayList;
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

import com.example.ejercicio_9_4.domain.Curso;
import com.example.ejercicio_9_4.domain.Tematica;
import com.example.ejercicio_9_4.dto.CursoDto;
import com.example.ejercicio_9_4.dto.CursoNuevoDto;
import com.example.ejercicio_9_4.services.CursoDtoConverter;
import com.example.ejercicio_9_4.services.CursoService;

import jakarta.validation.Valid;

@RestController
public class CursoController {

    @Autowired
    public CursoService cursoService;

    @Autowired
    private CursoDtoConverter cursoDtoConverter;

    @GetMapping("/curso")
    public ResponseEntity<?> getList() {
        List<Curso> listaCursos = cursoService.obtenerTodos();
        if (listaCursos.isEmpty()) {
            return ResponseEntity.notFound().build(); //devuelve el código 404 (Not found)
        } else {
            List<CursoDto> listaCursoDtos = new ArrayList<>();
            for(Curso curso : listaCursos) {
                listaCursoDtos.add(cursoDtoConverter.convertCursoToDto(curso));
            }
            // return ResposeEntity.status(HttpStatus.OK).body(listaCursos);  //en vez de hacerlo como abajo también se podría hacer así
            return ResponseEntity.ok(listaCursoDtos); //devuelve el código 200 (OK)
        }
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<?> getOneElement(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cursoDtoConverter.convertCursoToDto(curso)); //le paso el dto
        }
    }

    @PostMapping("/curso")
    public ResponseEntity<?> newElement(@Valid @RequestBody CursoNuevoDto nuevoCursoDto) {
        //@Valid si no se cumple la validación devuelve BAD_REQUEST y devuelve el código 400
        Curso curso = cursoDtoConverter.convertCursoDtoToCurso(nuevoCursoDto);
        Curso cursoSaved = cursoService.añadir(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSaved); //personalmente debería devolver un CursoDto y no Curso (tras haberlo guardado en la BBDD) ya que es lo que vuelves a mostrar al usuario
    }

    @PutMapping("/curso/{id}")
    public ResponseEntity<?> editElement(@PathVariable Long id, @Valid @RequestBody CursoNuevoDto editCursoDto) {
        //@Valid si no se cumple la validación devuelve BAD_REQUEST y devuelve el código 400
        Curso curso = cursoService.obtenerPorId(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        } else {
            curso = cursoDtoConverter.convertCursoDtoToCursoEdit(editCursoDto, id);
            Curso cursoSaved = cursoService.editar(curso); //guardo el curso en la BBDD y se lo paso al responseEntity
            return ResponseEntity.ok(cursoSaved);
        }
    }

    @DeleteMapping("/curso/{id}")
    public ResponseEntity<?> editElement(@PathVariable long id) {
        Curso curso = cursoService.obtenerPorId(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        } else {
            cursoService.borrar(id);
            return ResponseEntity.noContent().build(); //devuelve el código 204 (encontró el elemento a borrar)
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> showFindByName(@RequestParam String nombre) {
        List<Curso> listaCursos = cursoService.buscarPorNombre(nombre); //añado a un arraylist los cursos que encuentre por el nombre buscado
        if (listaCursos.isEmpty()) { //si el listado está vacío (es decir, no encontró nada por ese nombre)... 
            return ResponseEntity.notFound().build(); //retorna un 404
        } else { 
            List<CursoDto> listaCursosDto = new ArrayList<>();
            for(Curso curso : listaCursos) {
                listaCursosDto.add(cursoDtoConverter.convertCursoToDto(curso));
            }
            return ResponseEntity.ok(listaCursosDto); //si encontró algún curso por ese nombre retorna el listado de ellos (PERO EL DTO) con un 200
        }
    }

    @GetMapping("/findByTematica/{tematica}")
    public ResponseEntity<?> showFindByTematica(@PathVariable Tematica tematica) {
        List<Curso> listaCursos =  cursoService.buscarPorTematica(tematica);
        if (listaCursos.isEmpty()) {
            return ResponseEntity.notFound().build(); //retorna un 404
        } else {
            List<CursoDto> listaCursosDto = new ArrayList<>();
            for(Curso curso : listaCursos) {
                listaCursosDto.add(cursoDtoConverter.convertCursoToDto(curso));
            }
            return ResponseEntity.ok(listaCursosDto); //si encontró algún curso por ese nombre retorna el listado de ellos con un 200
        }
    }

    @GetMapping("/findByPrecioLessThan")
    public ResponseEntity<?> showFindByPrecioLessThan(@RequestParam Double precio) {
        List<Curso> listaCursos = cursoService.filtrarImporteMenorIgualPrecio(precio);
        if (listaCursos.isEmpty()) {
            return ResponseEntity.notFound().build(); //retorna un 404
        } else {
            List<CursoDto> listaCursosDto = new ArrayList<>();
            for(Curso curso : listaCursos) {
                listaCursosDto.add(cursoDtoConverter.convertCursoToDto(curso));
            }
            return ResponseEntity.ok(listaCursosDto); //si encontró algún curso por ese nombre retorna el listado de ellos con un 200
        }
    }
}