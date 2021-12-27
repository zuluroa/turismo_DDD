package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.values.ServicioID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.TipoDeServicio;

import java.util.Objects;

public class ServicioCreado extends DomainEvent {
    private final ServicioID servicioID;
    private final TipoDeServicio tipoDeServicio;

    public ServicioCreado(ServicioID servicioID, TipoDeServicio tipoDeServicio) {
        super("sofka.hospedaje.serviciocreado");
        this.servicioID = Objects.requireNonNull(servicioID, "El ID del servicio no puede ser nulo");
        this.tipoDeServicio = Objects.requireNonNull(tipoDeServicio, "El tipo de servicio no puede ser nulo");
    }

    public ServicioID getServicioID() {
        return servicioID;
    }

    public TipoDeServicio getTipoDeServicio() {
        return tipoDeServicio;
    }
}
