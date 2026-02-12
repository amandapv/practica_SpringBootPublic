package com.example.platilla.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.platilla.model.ClienteMedicamentos;
import com.example.platilla.model.ClienteRevision;
import com.example.platilla.model.ClienteTarifa;

import jakarta.annotation.PostConstruct;

@Configuration
//busca en el classpath la carpeta privado y el archivo precios.properties
@PropertySource("classpath:/privado/precios.properties")
public class PacienteConfig {
    
    @Value("${medicamento}")
    private int costoMedicamento;
    
    @Value("${revision}")
    private int costoRevision;
    
    @Value("${revision.jubilado}")
    private int costoRevisionJubilado;
    
    @Value("${tarifa}")
    private int costoTarifa;
    
    @PostConstruct
    public void initPacienteCostos() {
        // Inicializar los valores estáticos de cada clase

        ClienteMedicamentos.setCostoPorMedicamento(costoMedicamento);
        ClienteRevision.setCostoPorRevision(costoRevision);
        ClienteRevision.setCostoPorRevisionJubilado(costoRevisionJubilado);
        ClienteTarifa.setCostoConsulta(costoTarifa);
        System.out.println("=== Configuración de costos inicializada ===");
        System.out.println("Costo por medicamento: " + costoMedicamento);
        System.out.println("Costo por revisión: " + costoRevision);
        System.out.println("Costo por revisión jubilado: " + costoRevisionJubilado);
        System.out.println("Costo consulta/tarifa: " + costoTarifa);
        System.out.println("===========================================");
    }
}
