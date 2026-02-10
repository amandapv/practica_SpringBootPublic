package com.example.ejercicio_7_5.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Debes seleccionar un autor")
    @ManyToOne
    private Autor autor; 

    @ToStringExclude //para que no imprima en bucle al hacer el toString (imprimiría los vídeos, los que a su vez tienen un Curso asociado y los imprmiría y de nuevo se repetiría en bucle)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "curso", orphanRemoval = true) //fetch = FetchType.EAGER define la estrategia de carga de los datos: Si se pone la lista de vídeos como EAGER (lit.:ansioso/impaciente), siempre que busque un curso para mostrar sus detalles, los vídeos ya vendrán cargados dentro. Si fuera LAZY, Hibernate solo traería al Curso, la lista de vídeos se quedaría vacía (o como un "fantasma") hasta que se haga curso.getVideos(). Solo en ese momento, Hibernate iría a la base de datos a por ellos. MappedBy dice que no cree una tercera tabla (una tabla de unión), que la relación ya está definida en la columna "curso" que está dentro de la tabla Video (es decir, que hay un atributo "curso" en la tabla Video)
    private List<Video> videos = new ArrayList<>();
}
