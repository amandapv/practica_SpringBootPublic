package com.example.myapp.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
public class Empleado {
    
    @Id
    /*Codigo aquí a escribir */
    /*Cambiar el nombre a la columna */

    // Se genera automáticamente el id, con el medio de generación Identity, el cual delega la generación del id a la base de datos
/*Codigo aquí a escribir */
    private Long id;

    @NotEmpty
    private String nombre;

    @Email(message = "Debe tener formato email valido")
    private String email;

    private Double salario;

    private boolean enActivo;

    private Genero genero;

    // Asociación unidireccional: el atributo departamento de Empleado es el dueño de la relación
    // Departamento debe ser también una Entity
    // Borrado en cascada. Al borrar un departamento se borrarían automáticamente todos sus empleados. Se especifica junto al mapeo de la asociación
  /*Codigo aquí a escribir */
    private Departamento departamento;
}
