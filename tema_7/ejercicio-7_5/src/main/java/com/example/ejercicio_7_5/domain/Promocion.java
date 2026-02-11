package com.example.ejercicio_7_5.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor //submitea un constructor para todos los campos
@NoArgsConstructor //en cuanto se añade el @AllArgsConstructor, Java "borra" el constructor vacío automático. Pero Hibernate/JPA necesita el constructor vacío para poder recuperar datos de la base de datos. Sin esta, tu aplicación daría error al intentar leer de la BD. Este campo dice que cree un constrcutor vacío para que Hibernate pueda "funcionar"
@EqualsAndHashCode(of = "id")

@Entity // Le dice a JPA: "Esto es una tabla"
public class Promocion {
    
    @Id //marca el campo "id" como Clave Primaria (PK) para distinguir una promoción de otra
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "curso_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "influencer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Influencer influencer;

    @NotNull(message = "Debe incluir un precio")
    Double remuneracionPromoCurso;
    
}
