package com.example.crud_basico_pract.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Crea un constructor para todos los campos
@NoArgsConstructor //en cuanto se añade el @AllArgsConstructor, Java "borra" el constructor vacío automático. Pero Hibernate/JPA necesita el constructor vacío para poder recuperar datos de la base de datos. Sin esta, tu aplicación daría error al intentar leer de la BD.
@EqualsAndHashCode(of = "id")

public class Curso {

    @Min(value = 0)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    private Double precio;

    private Tematica tematica;
}
