package com.example.app;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.app.model.EstadoJuego;
import com.example.app.model.Intento;
import com.example.app.model.MasterMind;

@Service
@Scope("session")
public class MasterMindService {

    public MasterMind masterMind;

    //MÉTODO MIO----------------------------------------
    public void numeroIntentosJuego(String numIntentos) throws RuntimeException {
        int numInt;
        try { //intentamos...
            numInt = Integer.parseInt(numIntentos); //convertir el string que me llega en un entero
            masterMind.setMAX_INTENTOS(numInt); //modificar la variable con el número de intentos que acabo de convertir a entero 
            // System.out.println(numInt);
        } catch (RuntimeException e) { //si no lo consigue...
            throw new RuntimeException("Número de intentos no válido"); //lanza una excepción 
        }
        
        if (numInt <= 0) { //si el número de intentos es menor o igual a 0...
            throw new RuntimeException("El número de intentos debe ser mayor de 0"); //lanza una excepción
        }
    }


    public void nuevoJuego(String tamanoNumJuego) {
        masterMind = new MasterMind();  //inicializar el juego (Clase MaterMind con sus variables y métodos)

        int tamDig;

        try { //intentamos...
            tamDig = Integer.parseInt(tamanoNumJuego); //convertir el string que me llega en un entero

            masterMind.setTAM_NUMERO(tamDig); //modificar la variable con el número que acabo de convertir a entero 
            // System.out.println(tamDig);

        } catch (RuntimeException e) { //si no lo consigue...
            // System.out.println(e.getMessage());
            throw new RuntimeException("Tamaño introducido no válido"); //lanza una excepción 
        }

        do {
            masterMind.setNumeroSecreto(cadenaAlAzar(masterMind.TAM_NUMERO));
        } while (cadenaConDuplicados(masterMind.getNumeroSecreto()));
        
        masterMind.getListaIntentos().clear();
        masterMind.setEstadoJuego(EstadoJuego.JUGANDO);
        System.out.println("=====> Num secreto: " + masterMind.getNumeroSecreto()); // solo para testing
    }
    

    public String comprobarIntento(String intento) {
        
        if (masterMind.getEstadoJuego() != EstadoJuego.JUGANDO) {
            return "El juego ha finalizado"; //retornar esta string en caso de que el juego haya acabado 
        }
        if (cadenaConDuplicados(intento) || intento.length() != masterMind.TAM_NUMERO) {
            // System.out.println("patata 2");
            return "El número no coincide"; //retornar esta string en caso de que el número no coincida con el tamano del juegeo
        }
        // System.out.println(intento);
        int bienColocados = 0, malColocados = 0;
        String numeroSecreto = masterMind.getNumeroSecreto();
        for (int cont = 0; cont < intento.length(); cont++) {
            char letra = intento.charAt(cont);
            if (letra == numeroSecreto.charAt(cont))
                bienColocados++;
            else if (numeroSecreto.indexOf(letra) != -1)
                malColocados++;
        }
        masterMind.getListaIntentos().add(new Intento(intento, bienColocados, malColocados));

        // for(int i=masterMind.getListaIntentos().size(); i <= 0; i--) {
        //     System.out.println("intentos: " + i);
        // }

        // System.out.println("Número de intentos: " + masterMind.MAX_INTENTOS);
        System.out.println("Tamaño intentos: " + masterMind.getListaIntentos().size());
        if (bienColocados == masterMind.TAM_NUMERO){
            masterMind.setEstadoJuego(EstadoJuego.GANO);
            return "Has ganado"; //retornar esta string en caso de que el usuario haya ganado el juego
        } 
        if (masterMind.getListaIntentos().size() >= masterMind.MAX_INTENTOS) {
            masterMind.setEstadoJuego(EstadoJuego.PERDIO);
            return "Has perdido"; //retornar esta string en caso de que el usuario haya perdido el juego
        }
        return null; //no retorna nada por defecto, es decir que si no hay errores no retorna nada
    }


    // public ArrayList<Intento> litIntentosRestantes() {

    //     return masterMind.getListaIntentos();
    // }

    private boolean cadenaConDuplicados(String cad) {
        for (int i = 0; i < cad.length(); i++) {
            char c = cad.charAt(i);
            if (cad.indexOf(c, i + 1) != -1)
                return true;
        }
        return false;
    }

    private String cadenaAlAzar(int tamanho) {
        Random random = new Random();
        String cad = "";
        int x;
        for (int i = 0; i < tamanho; i++) {
            x = random.nextInt(10);
            cad += Integer.toString(x);
        }
        return cad;
    }
}
