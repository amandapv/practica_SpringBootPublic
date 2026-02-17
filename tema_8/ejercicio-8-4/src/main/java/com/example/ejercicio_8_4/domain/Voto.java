package com.example.ejercicio_8_4.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Voto {
    @NotEmpty(message = "El email es obligatorio")
    private String email = "";
    @NotNull(message = "La votación es obligatoria")
    private Integer foto = 0;
   
    //getters
    public String getEmail() {
        return email;
    }
    public Integer getFoto() {
        return foto;
    }

    //setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFoto(Integer foto) {
        this.foto = foto;
    }

}
