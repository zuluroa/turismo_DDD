package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;

import java.util.Objects;

public class FechaPlanDeViajeCreada extends DomainEvent {
    private final FechaPlanDeViaje fechaPlanDeViaje;

    public FechaPlanDeViajeCreada(FechaPlanDeViaje fechaPlanDeViaje) {
        super("sofka.plandeviaje.fechaplandeviajecreada");
        this.fechaPlanDeViaje = Objects.requireNonNull(fechaPlanDeViaje, "La Fecha de plan de viaje no puede ser nula");
    }

    public FechaPlanDeViaje getFechaPlanDeViaje() {
        return fechaPlanDeViaje;
    }
}
