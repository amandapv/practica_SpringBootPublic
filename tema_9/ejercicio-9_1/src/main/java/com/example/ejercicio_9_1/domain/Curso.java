package com.example.ejercicio_9_1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //Cr/editar/{id}/submitea un constructor para todos los campos
@NoArgsConstructor //en cuanto se añade el @AllArgsConstructor, Java "borra" el constructor vacío automático. Pero Hibernate/JPA necesita el constructor vacío para poder recuperar datos de la base de datos. Sin esta, tu aplicación daría error al intentar leer de la BD. Este campo dice que cree un constrcutor vacío para que Hibernate pueda "funcionar"
@EqualsAndHashCode(of = "id")

@Entity // Le dice a JPA: "Esto es una tabla"
public class Curso {
    @Id //marca el campo "id" como Clave Primaria (PK) para distinguir un curso de otro
    @GeneratedValue (strategy = GenerationType.IDENTITY)   //le dice a la BBDD que cada vez que inserte un curso, ésta le asigne el 1, luego el 2, etc
    // @NotNull(message = "El ID no puede estar vacío")
    @Min(value = 0, message = "El ID no puede ser inferior a 0")
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @Max(value = 5000, message = "No se admiten cursos de más de 5000 euros")
    private Double precio;

    private Tematica tematica;
}
