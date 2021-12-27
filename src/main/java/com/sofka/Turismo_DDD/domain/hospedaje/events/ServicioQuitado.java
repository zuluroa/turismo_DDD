package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.values.ServicioID;

import java.util.Objects;

public class ServicioQuitado extends DomainEvent {
    private final ServicioID servicioID;

    public ServicioQuitado(ServicioID servicioID) {
        super("sofka.hospedaje.servicioquitado");
        this.servicioID = Objects.requireNonNull(servicioID, "El ID del servicio no puede ser nulo");
    }

    public ServicioID getServicioID() {
        return servicioID;
    }
}
