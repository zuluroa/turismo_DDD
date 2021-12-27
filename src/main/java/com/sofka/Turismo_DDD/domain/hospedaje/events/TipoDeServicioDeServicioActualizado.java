package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Servicio;
import com.sofka.Turismo_DDD.domain.hospedaje.values.TipoDeServicio;

import java.util.Objects;

public class TipoDeServicioDeServicioActualizado extends DomainEvent {
    private final Servicio servicio;
    private final TipoDeServicio tipoDeServicio;

    public TipoDeServicioDeServicioActualizado(Servicio servicio, TipoDeServicio tipoDeServicio) {
        super("sofka.hospedaje.tipodeserviciodeservicioactualizado");
        this.servicio = Objects.requireNonNull(servicio, "El servicio no puede ser nulo");
        this.tipoDeServicio = Objects.requireNonNull(tipoDeServicio, "El tipo de servicio no puede ser nulo");
    }

    public Servicio getServicio() {
        return servicio;
    }

    public TipoDeServicio getTipoDeServicio() {
        return tipoDeServicio;
    }
}
