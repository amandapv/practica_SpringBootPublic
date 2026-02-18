package com.example.ejercicio_9_4.dto;

import com.example.ejercicio_9_4.domain.Tematica;

import lombok.Data;

@Data
public class CursoNuevoDto {
    private String nombre;
    private Double precio;
    private Tematica tematica;
    private Long idAutor;
}
