package com.sofka.Turismo_DDD.domain.hospedaje.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class PrecioTotalHospedaje implements ValueObject<Double> {
    private final Double value;

    public PrecioTotalHospedaje(Double value) {
        this.value = Objects.requireNonNull(value, "El precio de la habitacion no puede ser vacio");
        if(this.value.isNaN())throw new IllegalArgumentException("El precio de la habitacion debe ser de tipo número");
        if(this.value < 0)throw new IllegalArgumentException("El precio debe de la habitacion ser de mayor a 0");
    }

    public Double value() {
        return value;
    }

    @Override
    public String toString() {
        return "PrecioTotalHospedaje{" +
                "value=" + value +
                '}';
    }
}
