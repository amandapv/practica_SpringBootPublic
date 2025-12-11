package com.example.cuestionario.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Curso {

    @Min(value = 0)
    private Long id;

    @NotEmpty
    private String nombre;

    //@Email(message = "Debe tener formato email valido")
    //private String email;

    private Double precio;
    private Tematica tematica;

    //private boolean enActivo;

    //private Genero genero;
}
