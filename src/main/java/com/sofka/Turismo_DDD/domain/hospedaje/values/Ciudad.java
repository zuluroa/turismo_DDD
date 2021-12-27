package com.sofka.Turismo_DDD.domain.hospedaje.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

import static com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo.compararSiNuloOEsVacio;
import static com.sofka.Turismo_DDD.domain.utils.mayorOIgual.mayorOIgualQue;

public class Ciudad implements ValueObject<String> {
    private final String value;

    public Ciudad(String value) {
        this.value = Objects.requireNonNull(value, "La ciudad no puede ser nulo");
        if (compararSiNuloOEsVacio(this.value)) throw new IllegalArgumentException("La ciudad no puede ser vacio");
        if (mayorOIgualQue(this.value.length(), 4))
            throw new IllegalArgumentException("La ciudad debe ser mayor de 4 caracteres");
    }

    public String value() {
        return value;
    }
}
