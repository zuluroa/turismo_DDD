package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaRegreso;

import java.util.Objects;

public class FechaRegresoDeFechaPlanDeViajeActualizada extends DomainEvent {
    private final FechaPlanDeViaje fechaPlanDeViaje;
    private final FechaRegreso fechaRegreso;

    public FechaRegresoDeFechaPlanDeViajeActualizada(FechaPlanDeViaje fechaPlanDeViaje, FechaRegreso fechaRegreso) {
        super("sofka.plandeviaje.fecharegresoDefechaplandeviajeactualizada");
        this.fechaPlanDeViaje = Objects.requireNonNull(fechaPlanDeViaje, "La Fecha de plan de viaje no puede ser nula");
        this.fechaRegreso = Objects.requireNonNull(fechaRegreso, "La Fecha de plan de viaje de regreso no puede ser nula");
    }

    public FechaPlanDeViaje getFechaPlanDeViaje() {
        return fechaPlanDeViaje;
    }

    public FechaRegreso getFechaRegreso() {
        return fechaRegreso;
    }
}
