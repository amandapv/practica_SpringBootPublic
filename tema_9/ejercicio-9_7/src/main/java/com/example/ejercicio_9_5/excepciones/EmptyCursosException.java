package com.example.ejercicio_9_5.excepciones;

public class EmptyCursosException extends RuntimeException{
    
    public EmptyCursosException() {
        super("No hay cursos en el sistema");
    }
}
