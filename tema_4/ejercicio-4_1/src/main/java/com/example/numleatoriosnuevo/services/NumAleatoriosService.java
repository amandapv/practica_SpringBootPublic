package com.example.numleatoriosnuevo.services;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class NumAleatoriosService {
    
    Random random = new Random();
    private Set<Integer> lista = new LinkedHashSet<>();

    public Set<Integer> getList() { //devuelve la lista
        return lista;
    }

    //añadir nuevo número
    public void addNum() {
        boolean añadido;
        do {
            añadido = lista.add(random.nextInt(100) + 1);
        } while (!añadido);
    }


    public void deleteNum(Integer id) throws RuntimeException {
        if (!lista.remove(id)) { //si quiere eliminar el número pero aún no existe en la lista... (remove es un booleano. True si existe el número a eliminar, false si no existe)
            throw new RuntimeException("Ese número no existe en la lista");
        }
    }

}
