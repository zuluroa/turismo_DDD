package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.Actividad;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadPlanDeVIaje;

import java.util.Objects;

public class ActividadPlanDeViajeActualizado extends DomainEvent {
    private final Actividad actividad;
    private final ActividadPlanDeVIaje actividadPlanDeVIaje;

    public ActividadPlanDeViajeActualizado(Actividad actividad, ActividadPlanDeVIaje actividadPlanDeVIaje) {
        super("sofka.plandeviaje.actividadplanDeviajeactualizado");
        this.actividad = Objects.requireNonNull(actividad, "La actividad no puede ser nula");
        this.actividadPlanDeVIaje = Objects.requireNonNull(actividadPlanDeVIaje, "La actividad del plan de viaje no puede ser nulo");
    }

    public Actividad getActividad() {
        return actividad;
    }

    public ActividadPlanDeVIaje getActividadPlanDeVIaje() {
        return actividadPlanDeVIaje;
    }
}
