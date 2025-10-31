package com.example.palmares_act_form.services;

import org.springframework.stereotype.Service;

import com.example.palmares_act_form.Formulario;

@Service
public class FormServiceImpl {

    //método para convertir a mayúculas el nombre
    public String nombreMayus(Formulario form) {
        return form.getNombre().toUpperCase(); //retorno el nombre de la persona en mayúculas
    }


    //método para elegir el producto a enviar al cliente
    public String producto(Formulario form) throws RuntimeException {

        if (form.getProducto().equals("FOTO_FIRMADA")) {
            return form.getProducto();

        } else if (form.getProducto().equals("ENTRADA_VIP")) {
            return form.getProducto();

        } else if(form.getProducto().equals("BUFANDA")) {
            return form.getProducto();

        } else {
            throw new RuntimeException("Producto no válido");
        }
    }


    
}
