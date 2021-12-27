package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadPlanDeVIaje;

import java.util.Objects;

public class ActividadCreado extends DomainEvent {
    private final ActividadID actividadID;
    private final ActividadPlanDeVIaje actividadPlanDeVIaje;

    public ActividadCreado(ActividadID actividadID, ActividadPlanDeVIaje actividadPlanDeVIaje) {
        super("sofka.plandeviaje.actividadcreado");
        this.actividadID = Objects.requireNonNull(actividadID,"El ID de actividad no puede ser nulo");
        this.actividadPlanDeVIaje = Objects.requireNonNull(actividadPlanDeVIaje, "La actividad del plan de viaje no puede ser nulo");
    }

    public ActividadID getActividadID() {
        return actividadID;
    }

    public ActividadPlanDeVIaje getActividadPlanDeVIaje() {
        return actividadPlanDeVIaje;
    }
}
