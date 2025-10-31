package com.example.palmares_act_form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//añado con la librería Lombok los getters y los setters
@Getter
@Setter
public class Formulario {
    @NotEmpty(message = "Inroduzca el nombre")
    private String nombre;
    @NotEmpty(message = "Inroduzca el DNI")
    @Size(min=9, max=9, message = "Debe tener 9 dígitos")
    private String dni;
    @NotEmpty(message = "Inroduzca el email")
    @Email(message = "Formmato no válido")
    private String email;
    @NotEmpty(message = "Inroduzca la dirección de envío")
    private String direccionEnvio;
    @NotEmpty(message = "Seleccione un producto")
    private String producto; //podría hacer un ENUM
    @AssertTrue(message = "Es obligatorio aceptar las condiciones")
    private boolean aceptCondiones;
    private String archivo;
}
