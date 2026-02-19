package com.example.ejercicio_9_10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio_9_10.domain.CambioData;
import com.example.ejercicio_9_10.domain.FormConversion;

@Service
public class CotizacionMonedasService {
    
    @Autowired
    private RestClientService restClientService;

    public float cotizacionMonedas (FormConversion fConversion) {

        //guardo el resultado de la llamada a la api
        CambioData respuestaApi = restClientService.obtenerConversion(fConversion.getMonedaOrigen(), fConversion.getMonedaDestino());

        //obtengo la tasa de conversión de esa moneda (es un hashMap por lo que a través de su clave obtengo el valor)
        float tasaConversion = respuestaApi.getRates().get(fConversion.getMonedaDestino());

        return fConversion.getImporte() * tasaConversion;
    } 
}
