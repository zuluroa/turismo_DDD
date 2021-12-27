package com.sofka.Turismo_DDD.domain.genericvalues;

import co.com.sofka.domain.generic.ValueObject;
import com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo;
import com.sofka.Turismo_DDD.domain.utils.CorreoElectronicoValido;
import com.sofka.Turismo_DDD.domain.utils.mayorOIgual;

import java.util.Objects;

public class Contacto implements ValueObject<Contacto.Properties> {
    private final String telefono;
    private final String email;

    public Contacto(String telefono, String email ) {
        this.telefono = Objects.requireNonNull(telefono,"El telefono no puede ser nulo");
        this.email = Objects.requireNonNull(email,"El correo electronico no puede ser nulo");
        if(this.email.isBlank()) throw new IllegalArgumentException("El correo electronico no puede estar vacio");
        if(mayorOIgual.mayorOIgualQue(this.email.length(),10))throw new IllegalArgumentException("El correo electronico debe de tener minimo 10 caracteres");
        if(CompararVacioONulo.compararSiNuloOEsVacio(this.telefono)) throw new IllegalArgumentException("El telefono no puede estar vacio");
        if(mayorOIgual.mayorOIgualQue(this.telefono.length(), 7))throw new IllegalArgumentException("El telefono debe de tener minimo 7 caracteres");
        if(CorreoElectronicoValido.emailValido(email))throw new IllegalArgumentException("Debes ingresar un correo eleectronico valido");
    }


    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String telefono() {
                return telefono;
            }

            @Override
            public String email() {
                return email;
            }
        };
    }
    public interface  Properties {
        String telefono();
        String email();
    }
}
