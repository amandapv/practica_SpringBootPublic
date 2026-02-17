package com.example.myapp.security;


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
            auth // .requestMatchers("/registro/nuevo/**").permitAll()
                .requestMatchers("/user/**").hasRole("ADMIN")
                .requestMatchers("/cuentas", "/movimientos/").permitAll()
                .requestMatchers("/cuentas").hasAnyRole("ADMIN", "TITULAR", "USUARIO")
                .requestMatchers("/movimientos/**").hasAnyRole("ADMIN", "TITULAR", "USUARIO")
                .requestMatchers("/cuentas/**", "/movimientos/**").hasAnyRole("ADMIN", "TITULAR")
                
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //acceso a cualquiera a los recursos estáticos de la aplicación con nombre estándar
                .requestMatchers("/h2-console/**").hasRole("ADMIN")
                
                .anyRequest().authenticated()) //al resto de rutas le permito todo que estén autorizados
    
            .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                    .loginPage("/login") //mapping para mostrar el formulario de login (la que tengo definida en mi controlador)
                    .loginProcessingUrl("/login") //ruta post de /signin (a donde procesa mi ruta personalizada /signin)
                    .failureUrl("/login?error") //vuelve a /signin con mensaje de error
                    .defaultSuccessUrl("/", true).permitAll()) //la ruta por defecto al loguearse es / a la cual tiene acceso todo el mundo (/ redirige a /cuentas)
            .logout(logout -> logout
                    .logoutSuccessUrl("/signin?logout").permitAll()) //cuando se cierra la sesión irá a esa ruta y permitir acceso a todo el mundo
            .rememberMe(Customizer.withDefaults()) //oopción para que recuerde la sesión del usuario, la guarda en una cookie
            .httpBasic(Customizer.withDefaults()); //Habilita la autenticación HTTP Basic con la configuración por defecto
        http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/accessError")); //en caso de los usuariosn o tengan permisos suficientes para acceder a x sitio redirige a esta ruta
        return http.build();
    }
    
}
