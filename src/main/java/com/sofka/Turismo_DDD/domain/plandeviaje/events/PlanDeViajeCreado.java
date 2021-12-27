package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;

import java.util.Objects;

public class PlanDeViajeCreado extends DomainEvent {
    private final FechaPlanDeViaje fechaPlanDeViaje;
    private final CupoMaximo cupoMaximo;

    public PlanDeViajeCreado(FechaPlanDeViaje fechaPlanDeViaje, CupoMaximo cupoMaximo) {
        super("sofka.plandeviaje.plandeviajecreado");
        this.fechaPlanDeViaje = Objects.requireNonNull(fechaPlanDeViaje, "La fecha del plan de viaje no puede ser nulo");
        this.cupoMaximo = Objects.requireNonNull(cupoMaximo, "El cupo maximo no puede ser nulo");
    }

    public FechaPlanDeViaje getFechaPlanDeViaje() {
        return fechaPlanDeViaje;
    }

    public CupoMaximo getCupoMaximo() {
        return cupoMaximo;
    }
}
