package com.sofka.Turismo_DDD.domain.hospedaje.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

import static com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo.compararSiNuloOEsVacio;
import static com.sofka.Turismo_DDD.domain.utils.mayorOIgual.mayorOIgualQue;

public class TipoDeHabitacion implements ValueObject<String> {
    private final String value;

    public TipoDeHabitacion(String value) {
        this.value = Objects.requireNonNull(value, "El tipo de habitacion no puede ser nulo");
        if (compararSiNuloOEsVacio(this.value))
            throw new IllegalArgumentException("El tipo de habitacion no puede ser vacio");
        if (mayorOIgualQue(this.value.length(), 4))
            throw new IllegalArgumentException("El tipo de habitacion debe ser mayor de 4 caracteres");
    }

    public String value() {
        return value;
    }
}
