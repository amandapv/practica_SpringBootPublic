package com.example.ejercicio_9_4.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ejercicio_9_4.domain.Curso;
import com.example.ejercicio_9_4.dto.CursoDto;
import com.example.ejercicio_9_4.dto.CursoNuevoDto;

@Component
public class CursoDtoConverter {
    
    @Autowired
    private ModelMapper modelMapper; //llamo a mi ModelMapper creado en mi archivo ModelMapperConfig gracias al @Bean

    @Autowired
    private AutorService autorService;

    //para mostrar los datos del dto
    public CursoDto convertCursoToDto(Curso curso) {
        return modelMapper.map(curso, CursoDto.class);
    }

    //para convertir el DTO del curso que me llega a un Curso "normal" y así poder hacer un save en la BBDD (me llega el dto de creación)
    public Curso convertCursoDtoToCurso(CursoNuevoDto cursoNuevoDto) {
        return new Curso(
            null, 
            cursoNuevoDto.getNombre(), 
            cursoNuevoDto.getPrecio(), 
            cursoNuevoDto.getTematica(), 
            autorService.obtenerPorId(cursoNuevoDto.getIdAutor())
        );
    }

    //para convertir el DTO del curso que me llega a un curso "normal" y así poder hacer un save en la BBDD PERO como es para una edición necesito también su ID!!! (me llega el dto de creación)
    public Curso convertCursoDtoToCursoEdit(CursoNuevoDto cursoEditDto, long id) {
        Curso curso = convertCursoDtoToCurso(cursoEditDto);   
        curso.setId(id);
        return curso;
    }
}
