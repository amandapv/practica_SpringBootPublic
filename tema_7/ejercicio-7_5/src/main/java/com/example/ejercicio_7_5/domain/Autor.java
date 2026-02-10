package com.example.ejercicio_7_5.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //submitea un constructor para todos los campos
@NoArgsConstructor //en cuanto se añade el @AllArgsConstructor, Java "borra" el constructor vacío automático. Pero Hibernate/JPA necesita el constructor vacío para poder recuperar datos de la base de datos. Sin esta, tu aplicación daría error al intentar leer de la BD. Este campo dice que cree un constrcutor vacío para que Hibernate pueda "funcionar"
@EqualsAndHashCode(of = "id")

@Entity // Le dice a JPA: "Esto es una tabla"
public class Autor {

    @Id //marca el campo "id" como Clave Primaria (PK) para distinguir un autor de otro
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true) //hacemos que la columna no pueda contener duplicados, es decir, que no puede haber más de un nombre igual
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(unique=true)
    @Email(message = "Formato no admitido")
    private String email;

    private Double limiteCosteTotalCursos;
}
