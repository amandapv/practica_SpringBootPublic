package com.example.ejercicio_8_4.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    } 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.headers(
            headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.authorizeHttpRequests( 
            auth -> 
            auth.requestMatchers("...").permitAll() //la vista de todos los cursos será accesible para todos (no necesitas estar logueado)
                .requestMatchers("/nuevo", "/nuevo/**").hasAnyRole("USER", "ADMIN") //los roles "user" y "admin" podrán crear cursos 
                .requestMatchers("/editar/**", "/borrar/**").hasRole("ADMIN") //el rol "ADMIN" podrá editar y borrar cursos también
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //acceso a cualquiera a los recursos estáticos de la aplicación con nombre estándar
                .requestMatchers("/h2-console/**").hasRole("ADMIN")
                .requestMatchers("/**").permitAll() //se mostrará su id, findByName... (no necesitas estar logueado) -- LA MOVÍ AL "FINAL" PORQUE SI VA AL INICIO Ignorará todas las reglas que PONGA debajo porque ya encontró una que encaja
                .anyRequest().authenticated()) //al resto de rutas le permito todo todo que estén autorizados

            .formLogin(formLogin -> formLogin.defaultSuccessUrl("/", true)) //permitir acceso a la ruta de /login a todo el mundo
            .logout(logout -> logout.permitAll()) //permitir acceso a la ruta de /logout a todo el mundo
            .httpBasic(Customizer.withDefaults()); //Habilita la autenticación HTTP Basic con la configuración por defecto
        http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/accessError")); //en caso de los usuariosn o tengan permisos suficientes para acceder a x sitio redirige a esta ruta
        return http.build();
    }
}
