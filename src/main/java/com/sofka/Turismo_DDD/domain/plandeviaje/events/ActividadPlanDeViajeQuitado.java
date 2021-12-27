package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadID;

import java.util.Objects;

public class ActividadPlanDeViajeQuitado extends DomainEvent {

    private final ActividadID actividadID;

    public ActividadPlanDeViajeQuitado(ActividadID actividadID) {
        super("sofka.plandeviaje.actividadplanDeviajequitado");
        this.actividadID = Objects.requireNonNull(actividadID,"El ID de actividad no puede ser nulo");
    }

    public ActividadID getActividadID() {
        return actividadID;
    }
}
