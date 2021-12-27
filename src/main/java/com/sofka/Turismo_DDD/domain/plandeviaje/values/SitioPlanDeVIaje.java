package com.sofka.Turismo_DDD.domain.plandeviaje.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

import static com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo.compararSiNuloOEsVacio;
import static com.sofka.Turismo_DDD.domain.utils.mayorOIgual.mayorOIgualQue;

public class SitioPlanDeVIaje implements ValueObject<String> {
    private final String value;

    public SitioPlanDeVIaje(String value) {
        this.value = Objects.requireNonNull(value, "El sitio del plan de viaje no puede ser nulo");
        if(compararSiNuloOEsVacio(this.value))throw new IllegalArgumentException("El sitio del plan de viaje no puede ser vacio");
        if(mayorOIgualQue(this.value.length() , 6)) throw new IllegalArgumentException("El sitio del plan de viaje debe ser mayor de 3 caracteres");
    }

    public String value() {
        return value;
    }
}
