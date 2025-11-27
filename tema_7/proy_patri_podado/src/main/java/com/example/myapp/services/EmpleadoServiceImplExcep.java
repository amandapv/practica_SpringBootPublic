package com.example.myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.myapp.domain.Departamento;
import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;
import com.example.myapp.repositories.EmpleadoRepository;

@Service
public class EmpleadoServiceImplExcep implements EmpleadoService {

    // Inyectamos el repositorio de Empleado para poder hacer las operaciones
    /*Codigo aquí a escribir */

    private final Double MIN_SALAR = 18000D;

    public List<Empleado> obtenerTodos() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public Empleado obtenerPorId(long id) throws RuntimeException {
        return /*Codigo aquí a escribir */;
        // findById de JpaRepository devuelve un Optional. Para simplificar,
        // y que el servicio siga devolviendo Empleado y no Optional<Empleado>
        // hacemos que si no lo encuentra, lance una excepción. La otra opción sería
        // que devolviese null: return repositorio.findById(id).orElse(null);
    }

    public Empleado añadir(Empleado empleado) throws RuntimeException {
        // Comprobamos que el salario no sea muy bajo
        if (empleado.getSalario() < MIN_SALAR)
            throw new RuntimeException("Salario muy bajo");
        Departamento departamento = empleado.getDepartamento();
        // Comprobamos que no se sobrepase el presupuesto del departamento
        Double sumaSalariosDepto = obtenerSumaSalarioPorDepartamento(departamento);
        if (sumaSalariosDepto + empleado.getSalario() > departamento.getPresupuesto())
           throw new RuntimeException("Sobrepasa presupuesto de departamento");
        return repositorio.save(empleado);
    }

    public Empleado editar(Empleado empleado) throws RuntimeException {
        //empleado contiene los datos nuevos, empleadoSinEditar los datos antiguos
        Empleado empleadoSinEditar = obtenerPorId(empleado.getId()); // lanza excepción si no existe
        // Comprobamos que el salario no sea muy bajo
        if (empleado.getSalario() < MIN_SALAR)
            throw new RuntimeException("Salario muy bajo");

        Departamento departamentoNuevo = empleado.getDepartamento();
        Departamento departamentoViejo = empleadoSinEditar.getDepartamento();
        Double sumaSalariosDeptoNuevo = obtenerSumaSalarioPorDepartamento(departamentoNuevo);
        
        if (departamentoNuevo.equals(departamentoViejo))  {  //no cambia de departamento
            // Comprobamos que no se sobrepase el presupuesto del departamento
            if (sumaSalariosDeptoNuevo + empleado.getSalario() - empleadoSinEditar.getSalario()> departamentoNuevo.getPresupuesto())
               throw new RuntimeException("Sobrepasa presupuesto de departamento");
        }
        else { //cambia de departamento 
            // Comprobamos que no se sobrepase el presupuesto del departamento nuevo
            if (sumaSalariosDeptoNuevo + empleado.getSalario() > departamentoNuevo.getPresupuesto())
            throw new RuntimeException("Sobrepasa presupuesto de departamento");
        }
        return repositorio.save(empleado);
    }

    public void borrar(Long id) throws RuntimeException {
        obtenerPorId(id); // lanza excepción si no existe
        repositorio.deleteById(id);
    }

    public List<Empleado> buscarPorNombre(String textoNombre) {
        return repositorio.findByNombreContainingIgnoreCase(textoNombre);
    }

    public List<Empleado> buscarPorGenero(Genero genero) {
        return repositorio.findByGenero(genero);
    }

    public List<Empleado> obtenerEmpleadosSalarioMayor(double salario) {
        return repositorio.findBySalarioGreaterThanEqualOrderBySalario(salario);
    }

    public List<Empleado> obtenerEmpleadoSalarioMayorMedia() {
        return repositorio.queryBySalarioOverAverage();
    }

    public Double obtenerSumaSalarioPorDepartamento(Departamento departamento){
        return repositorio.querySumSalariosByDepartamento (departamento).orElse(0d);
    }

    public List<Empleado> obtenerPorDepartamento(Long idDepto){
        return repositorio.findByDepartamentoId(idDepto);
    }

}
