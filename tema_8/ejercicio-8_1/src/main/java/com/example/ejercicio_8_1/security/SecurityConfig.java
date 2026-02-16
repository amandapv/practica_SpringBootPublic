package com.example.ejercicio_8_1.security;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    @Bean
    public UserDetailsService users (PasswordEncoder passwordEncoder) {
        //creamos el usuario "user"
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("1234"))
            .roles("USER")
            .build();
        //creamos el usuario "admin"
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("1234"))
            .roles("ADMIN")
            .build();
        //retornamos ambos usuarios en memoria (NO BBDD)
        return new InMemoryUserDetailsManager(user, admin);
    }
    
    @Bean
    //es lo mismo que lo de abajo pero me arregló la estrucutra la IA y se ve mejor así la verdad...
    // public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
    //     http.headers(
    //         headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
    //     http.authorizeHttpRequests( 
    //         auth -> auth.requestMatchers("/", "/list").permitAll() //la vista de todos los cursos será accesible para todos (no necesitas estar logueado)
    //                     .requestMatchers("/nuevo", "/nuevo/**").hasAnyRole("USER", "ADMIN") //los roles "user" y "admin" podrán crear cursos 
    //                     .requestMatchers("/editar/**", "/borrar/**").hasRole("ADMIN") //el rol "ADMIN" podrá editar y borrar cursos también
    //                     .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //acceso a cualquiera a los recursos estáticos de la aplicación con nombre estándar
    //                     .requestMatchers("/h2-console/**").hasRole("ADMIN")
    //                     .requestMatchers("/**").permitAll() //se mostrará su id, findByName... (no necesitas estar logueado) -- LA MOVÍ AL "FINAL" PORQUE SI VA AL INICIO Ignorará todas las reglas que PONGA debajo porque ya encontró una que encaja
    //                     .anyRequest().authenticated() //al resto de rutas le permito todo todo que estén autorizados
    //     );
    //     http.formLogin(form -> form.defaultSuccessUrl("/", true)); //permitir acceso a la ruta de /login a todo el mundo
    //     http.logout(logout -> logout.permitAll()); //permitir acceso a la ruta de /logout a todo el mundo
    //     http.httpBasic(Customizer.withDefaults()); //Habilita la autenticación HTTP Basic con la configuración por defecto
    //     http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/accessError")); //en caso de los usuariosn o tengan permisos suficientes para acceder a x sitio redirige a esta ruta
    //     return http.build();
    // }
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.headers(
            headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.authorizeHttpRequests( 
            auth -> 
            auth.requestMatchers("/", "/list").permitAll() //la vista de todos los cursos será accesible para todos (no necesitas estar logueado)
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
