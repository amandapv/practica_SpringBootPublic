package com.example.ejercicio_9_10.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.ejercicio_9_10.domain.CambioData;

@Service
public class RestClientService {
    
    private RestClient restClient;

    public RestClientService() {
        this.restClient = RestClient.create("https://api.frankfurter.dev/v1/latest");
    }

    public CambioData obtenerConversion(String origen, String destino) throws RuntimeException {
        String url = "?from="+origen+"&to="+destino;
        ResponseEntity<CambioData> cambiaData = restClient.get().uri(url).retrieve().toEntity(CambioData.class);
        return cambiaData.getBody();
    }
}
