package com.example.ejerImplementar;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //interfaz - para que se ejecute al iniciar la aplicación e implementar los métodos de configuración que interesen

public class webMvcConfigController implements WebMvcConfigurer{
    @Override

    public void addViewControllers (ViewControllerRegistry registry) {
        
    }
}
