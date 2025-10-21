package com.example.palmares_act_form;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //interfaz - para que se ejecute al iniciar la aplicación e implementar los métodos de configuración que interesen

//para evitar tener un controlador (getMapping) de páginas estáticas (que no tienen nigúna lógica más que mostrar lo necesario como Quienes somos), creamos esta clase para meterlos aquí y no tener un churro gigante de getMapings
public class webMvcConfigController implements WebMvcConfigurer{
    @Override

    public void addViewControllers (ViewControllerRegistry registry) {
        registry.addViewController("/app/enlaces-externos").setViewName("linksView");
    }
}
