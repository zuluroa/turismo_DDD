package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;

import java.util.Objects;

public class PlanDeViajeAsociado extends DomainEvent {

    private PlanDeViajeID planDeViajeID;

    public PlanDeViajeAsociado(PlanDeViajeID planDeViajeID) {
        super("sofka.reserva.planDeviajeasociado");
        this.planDeViajeID = Objects.requireNonNull(planDeViajeID, "El ID de plan de viaje no debe estar nulo");
    }

    public PlanDeViajeID getPlanDeViajeID() {
        return planDeViajeID;
    }
}
