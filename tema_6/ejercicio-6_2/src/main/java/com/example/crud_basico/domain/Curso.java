package com.example.crud_basico.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Curso {

    @NotNull(message = "El ID no puede estar vacío")
    @Min(value = 0, message = "El ID debe ser mayor de 0")
    private Long id;

    @NotEmpty(message = "El nombre del curso no puede estar vacío")
    private String nombre;

    @Max(value = 5000, message = "El valor máximo del curso es de 5000 euros")
    private Double precio;
    private Tematica tematica;
}
