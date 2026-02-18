package com.example.ejercicio_9_5.excepciones;

public class CursoNotFoundException extends RuntimeException {
    
    public CursoNotFoundException (long id) {
        super("No se puede encontrar el curso con ID: " + id);
    }
}
