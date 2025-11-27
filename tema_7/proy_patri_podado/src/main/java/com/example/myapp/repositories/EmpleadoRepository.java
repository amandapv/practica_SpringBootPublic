package com.example.myapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.myapp.domain.Departamento;
import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    /* Métodos derivados de la interfaz JPARepository por un o varios atributos de la clase Empleado -->No hay que implementarlos
        findBy + atributo + operador + And/Or + atributo + operador + And/Or + atributo + operador + ...

        Pueden ser varios atributos unidos por “And” o “Or”. Por convención, debemos emplear notación “camelcase”, es decir, el principio 
        de cada palabra (incluyendo los nombres de atributos) deberá empezar por mayúscula y el resto en minúscula. 
        Partículas que podemos añadir:
        And, Or, Is, Equals, Between, LessThan, LessThanEqual, GreaterThan, GreaterThanEqual, After, Before, IsNull, 
        IsNotNull, NotNull, Like, NotLike, StartingWith, EndingWith, Containing, OrderBy, Not, In, NotIn, True, False, IgnoreCase.

        Podemos ordenar con OrderBy + atributo + "Asc" o "Desc" (por defecto es "Asc")
    */ 
    List<Empleado> findBySalarioGreaterThanEqualOrderBySalario(double salario);

    //Es importante que el nombre del método empiece por "findBy" para que Spring Data JPA lo interprete como una consulta
    // y que al comprobar el nombre del atributo, lo haga de forma insensible a mayúsculas y minúsculas
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);

    List<Empleado> findByGenero(Genero genero);

    List<Empleado> findByDepartamentoId(Long idDepto);


    /* Métodos Query, para trabajar con consultas a medida, con la anotación @Query y el lenguaje JPQL. 
     * Primero incluimos la consulta JPQL y después la firma del método que invoca esa consulta
    */
/*Codigo aquí a escribir */
    List<Empleado> queryBySalarioOverAverage();


    // Devuelve la suma de los salarios de los empleados de un departamento, puede devolver un nulo (de ahí el uso de Optional)
 /*Codigo aquí a escribir */
    Optional <Double> querySumSalariosByDepartamento(@Param("departamento") Departamento departamento);
}
