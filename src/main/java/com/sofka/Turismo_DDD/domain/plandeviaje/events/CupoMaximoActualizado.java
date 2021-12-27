package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.PlanDeViaje;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;

import java.util.Objects;

public class CupoMaximoActualizado extends DomainEvent {
    private final PlanDeViaje planDeViaje;
    private final CupoMaximo cupoMaximo;

    public CupoMaximoActualizado(PlanDeViaje planDeViaje, CupoMaximo cupoMaximo) {
        super("sofka.plandeviaje.cupomaximoactualizado");
        this.planDeViaje = Objects.requireNonNull(planDeViaje,"El plan de viaje no puede ser nulo");
        this.cupoMaximo = Objects.requireNonNull(cupoMaximo, "El cupo maximo no puede ser nulo");
    }

    public PlanDeViaje getPlanDeViaje() {
        return planDeViaje;
    }

    public CupoMaximo getCupoMaximo() {
        return cupoMaximo;
    }
}
