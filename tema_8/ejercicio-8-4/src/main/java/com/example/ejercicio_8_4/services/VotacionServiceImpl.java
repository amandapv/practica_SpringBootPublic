package com.example.ejercicio_8_4.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.ejercicio_8_4.domain.Voto;
import com.example.ejercicio_8_4.interfaces.VotacionService;

@Service //indicarle que es un servicio
public class VotacionServiceImpl implements VotacionService{

    private ArrayList<String> listadoEmail = new ArrayList<>();
    private ArrayList<Integer> listadoVoto = new ArrayList<>(Arrays.asList(0, 0, 0)); //inicializo un array que almacenará la votación de cada foto. Lo pongo fuera del siguiente método para que no se inicialice a 0 cada vez que vaya a la votación 

    //getters
    public ArrayList<String> getListadoEmail() {
        return listadoEmail;
    }
    public ArrayList<Integer> getListadoVoto() {
        return listadoVoto;
    }
    
    @Override //no es obligatorio, pero muy recomendable por dos razones principales: Verificación del Compilador (verifica si el método que estás escribiendo realmente existe en la clase padre o en la interfaz que estás implementando.) y Legibilidad del Código (actúa como un marcador visual).
    //método para hacer una votación
    public void votacion(Voto form) throws RuntimeException{

        if (!listadoEmail.contains(form.getEmail())) { //si en el arraylist de listado de emails NO hay un correo igual 

            listadoEmail.add(form.getEmail()); //añadimos el email al listado de emails
            
            listadoVoto.set(form.getFoto(), listadoVoto.get(form.getFoto()) + 1); //añado al listado de votos, un nuevo voto a la foto elegida
        } else {
            throw new RuntimeException("Ya ha realizado una votación con el correo: " + form.getEmail());
        }

    }
}
