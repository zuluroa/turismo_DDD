package com.sofka.Turismo_DDD.domain.genericvalues;

import co.com.sofka.domain.generic.ValueObject;
import com.sofka.Turismo_DDD.domain.utils.mayorOIgual;

import java.util.Objects;

public class Nombre implements ValueObject<Nombre.Properties> {
    private final String nombres;
    private final String apellidos;

    public Nombre(String nombres, String apellidos) {
        this.nombres = Objects.requireNonNull(nombres, "El nombre no puede ser nulo");
        this.apellidos = Objects.requireNonNull(apellidos, "El apellido no puede ser nulo");
        if (this.apellidos.isBlank()) throw new IllegalArgumentException("El apellido no puede estar vacio");
        if (mayorOIgual.mayorOIgualQue(this.apellidos.length(), 5))
            throw new IllegalArgumentException("El apellido debe de tener minimo 5 caracteres");
        if (this.nombres.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (mayorOIgual.mayorOIgualQue(this.nombres.length(), 3))
            throw new IllegalArgumentException("El nombre debe de tener minimo 3 caracteres");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String nombres() {
                return nombres;
            }

            @Override
            public String apellidos() {
                return apellidos;
            }
        };
    }

    public interface Properties {
        String nombres();

        String apellidos();
    }
}
