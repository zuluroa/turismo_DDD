package com.sofka.Turismo_DDD.domain.plandeviaje.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

import static com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo.compararSiNuloOEsVacio;
import static com.sofka.Turismo_DDD.domain.utils.mayorOIgual.mayorOIgualQue;

public class ActividadPlanDeVIaje implements ValueObject<String> {
    private final String value;

    public ActividadPlanDeVIaje(String value) {
        this.value = Objects.requireNonNull(value, "La actividad no puede ser nulo");
        if (compararSiNuloOEsVacio(this.value)) throw new IllegalArgumentException("La actividad no puede ser vacio");
        if (mayorOIgualQue(this.value.length(), 6))
            throw new IllegalArgumentException("La actividad debe ser mayor de 6 caracteres");
    }

    public String value() {
        return value;
    }
}
