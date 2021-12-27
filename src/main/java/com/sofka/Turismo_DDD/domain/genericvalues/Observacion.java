package com.sofka.Turismo_DDD.domain.genericvalues;

import co.com.sofka.domain.generic.ValueObject;
import com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo;
import com.sofka.Turismo_DDD.domain.utils.mayorOIgual;

import java.util.Objects;

public class Observacion implements ValueObject<String> {

    private final String observacion;

    public Observacion(String observacion) {
        this.observacion = Objects.requireNonNull(observacion, "La observacion no puede ser nula");
        if (CompararVacioONulo.compararSiNuloOEsVacio(observacion))
            throw new IllegalArgumentException("La observacion no puede ser vacia");
        if (mayorOIgual.mayorOIgualQue(observacion.length(), 5))
            throw new IllegalArgumentException("La observacion debe tener más de 5 caracteres");
    }

    @Override
    public String value() {
        return observacion;
    }
}
