package com.sofka.Turismo_DDD.domain.genericvalues;

import co.com.sofka.domain.generic.ValueObject;
import com.sofka.Turismo_DDD.domain.utils.mayorOIgual;

import java.util.Objects;

public class Identificacion implements ValueObject<Identificacion.Properties> {

    private final String tipoDeIdentificacion;
    private final String numeroDeIdentificacion;

    public Identificacion(String tipoDeIdentificacion, String numeroDeIdentificacion ) {
        this.tipoDeIdentificacion = Objects.requireNonNull(tipoDeIdentificacion,"El tipo de identificación no puede ser nulo");
        this.numeroDeIdentificacion = Objects.requireNonNull(numeroDeIdentificacion,"El numero de identificación no puede ser nulo");
        if(this.numeroDeIdentificacion.isBlank()) throw new IllegalArgumentException("El numero de identificación no puede estar vacio");
        if(mayorOIgual.mayorOIgualQue(this.numeroDeIdentificacion.length(),10))throw new IllegalArgumentException("El numero de identificación debe de tener  10 caracteres");
        if(this.tipoDeIdentificacion.isBlank()) throw new IllegalArgumentException("El tipo de identificación no puede estar vacio");
        if(mayorOIgual.mayorOIgualQue(this.tipoDeIdentificacion.length(), 2))throw new IllegalArgumentException("El numero de identificación debe de tener minimo 2 caracteres");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String tipoDeIdentificacion() {
                return tipoDeIdentificacion;
            }

            @Override
            public String numeroDeIdentificacion() {
                return numeroDeIdentificacion;
            }
        };
    }
    public interface  Properties {
        String tipoDeIdentificacion();
        String numeroDeIdentificacion();
    }

}
