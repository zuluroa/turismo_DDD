package com.sofka.Turismo_DDD.domain.genericvalues;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class CupoMaximo implements ValueObject<Integer> {
    private final Integer value;

    public CupoMaximo(Integer value) {
        this.value = Objects.requireNonNull(value, "El cupo maximo no puede ser vacio");
        if (this.value < 0) throw new IllegalArgumentException("El cupo maximo ser de mayor a 0");
    }

    public Integer value() {
        return value;
    }
}
