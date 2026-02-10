package com.example.ejercicio_7_5.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //submitea un constructor para todos los campos
@NoArgsConstructor //en cuanto se añade el @AllArgsConstructor, Java "borra" el constructor vacío automático. Pero Hibernate/JPA necesita el constructor vacío para poder recuperar datos de la base de datos. Sin esta, tu aplicación daría error al intentar leer de la BD. Este campo dice que cree un constrcutor vacío para que Hibernate pueda "funcionar"
@EqualsAndHashCode(of = "id")

@Entity // Le dice a JPA: "Esto es una tabla"
public class Video {
    @Id //marca el campo "id" como Clave Primaria (PK) para distinguir un video de otro
    @GeneratedValue (strategy = GenerationType.IDENTITY) //le dice a la BBDD que cada vez que inserte un video, ésta le asigne el 1, luego el 2, etc
    @Min(value = 0, message = "El ID no puede ser inferior a 0")
    private Long id;

    private String descripcion;

    private int duracionSegundos;

    private String youtubeId;

    @NotNull(message = "Debes seleccionar un curso")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE) //si solo dejo "CascadeType.ALL" en el Curso para que todas las sentencias se reflejen automáticamente en los videos (eliminar, añadir, actualizar...), entonces si desde la BBDD elimino un curso (sin pasar por Java es decir, por el JPA Hibernate) la propia BBDD se encarga de borrar los vídeos automáticamente. Si no tuviera esta línea, Y LO ELIMINO DESDE LA BBDD quedaría el video sin borrar (obviamente si lo elimino desde el JPA si que se eliminaría aunque no tuviera esta línea)
    private Curso curso;
}
