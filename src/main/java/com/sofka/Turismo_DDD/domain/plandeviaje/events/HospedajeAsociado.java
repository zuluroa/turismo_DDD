package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.Hospedaje;

import java.util.Objects;

public class HospedajeAsociado extends DomainEvent {
    private final Hospedaje hospedaje;

    public HospedajeAsociado(Hospedaje hospedaje) {
        super("sofka.plandeviaje.hospedajeasociado");
        this.hospedaje = Objects.requireNonNull(hospedaje, "El hospedaje no puede ser nulo");
    }

    public Hospedaje getHospedaje() {
        return hospedaje;
    }
}
