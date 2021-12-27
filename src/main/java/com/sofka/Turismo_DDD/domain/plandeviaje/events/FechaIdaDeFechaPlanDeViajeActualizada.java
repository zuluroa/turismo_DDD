package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaIda;

import java.util.Objects;

public class FechaIdaDeFechaPlanDeViajeActualizada extends DomainEvent {
    private final FechaPlanDeViaje fechaPlanDeViaje;
    private final FechaIda fechaIda;

    public FechaIdaDeFechaPlanDeViajeActualizada(FechaPlanDeViaje fechaPlanDeViaje, FechaIda fechaIda) {
        super("sofka.plandeviaje.fechaidaDefechaplandeviajeactualizada");
        this.fechaPlanDeViaje = Objects.requireNonNull(fechaPlanDeViaje, "La Fecha de plan de viaje no puede ser nula");
        this.fechaIda = Objects.requireNonNull(fechaIda, "La Fecha de plan de viaje de ida no puede ser nula");
    }

    public FechaPlanDeViaje getFechaPlanDeViaje() {
        return fechaPlanDeViaje;
    }

    public FechaIda getFechaIda() {
        return fechaIda;
    }
}
