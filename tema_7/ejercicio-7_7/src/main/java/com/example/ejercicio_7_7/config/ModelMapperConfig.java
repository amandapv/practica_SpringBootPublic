package com.example.ejercicio_7_7.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean //crear un Bean de tipo ModelMapper para toda la aplicación y así poder inyectar una instancia e invocar a sus métodos donde se necesite
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
