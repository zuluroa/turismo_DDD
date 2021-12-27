package com.sofka.Turismo_DDD.domain.genericvalues;

import co.com.sofka.domain.generic.ValueObject;
import com.sofka.Turismo_DDD.domain.utils.mayorOIgual;

import java.util.Objects;

public class Direccion implements ValueObject<Direccion.Properties> {
    private final String ciudad;
    private final String direccion;

    public Direccion(String ciudad, String direccion) {
        this.ciudad = Objects.requireNonNull(ciudad, "La ciudad no puede ser nula");
        this.direccion = Objects.requireNonNull(direccion, "La dirección no puede ser nula");
        if (direccion.isBlank()) throw new IllegalArgumentException("La dirección no puede estar vacio");
        if (mayorOIgual.mayorOIgualQue(direccion.length(), 10))
            throw new IllegalArgumentException("La dirección debe de tener minimo 10 caracteres");
        if (ciudad.isBlank()) throw new IllegalArgumentException("La ciudad no puede estar vacia");
        if (mayorOIgual.mayorOIgualQue(ciudad.length(), 5))
            throw new IllegalArgumentException("La ciudad debe de tener minimo 5 caracteres");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String ciudad() {
                return ciudad;
            }

            @Override
            public String direccion() {
                return direccion;
            }
        };
    }

    public interface Properties {
        String ciudad();

        String direccion();
    }
}
