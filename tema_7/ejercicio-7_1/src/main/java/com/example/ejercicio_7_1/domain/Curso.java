package com.example.ejercicio_7_1.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Crea un constructor para todos los campos
@NoArgsConstructor //en cuanto se añade el @AllArgsConstructor, Java "borra" el constructor vacío automático. Pero Hibernate/JPA necesita el constructor vacío para poder recuperar datos de la base de datos. Sin esta, tu aplicación daría error al intentar leer de la BD.
@EqualsAndHashCode(of = "id")

public class Curso {

    @NotNull(message = "El ID no puede estar vacío")
    @Min(value = 0, message = "El ID no puede ser inferior a 0")
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @Max(value = 5000, message = "No se admiten cursos de más de 5000 euros")
    private Double precio;

    private Tematica tematica;
}
