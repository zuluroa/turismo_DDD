package com.sofka.Turismo_DDD.domain.plandeviaje.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadPlanDeVIaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;

import java.util.Objects;

public class CrearActividadCommand extends Command {
    private final PlanDeViajeID planDeViajeID;
    private final ActividadID actividadID;
    private final ActividadPlanDeVIaje actividadPlanDeVIaje;

    public CrearActividadCommand(PlanDeViajeID planDeViajeID, ActividadID actividadID, ActividadPlanDeVIaje actividadPlanDeVIaje) {
        this.planDeViajeID = Objects.requireNonNull(planDeViajeID, "El ID de plan de viaje no puede ser nulo");
        this.actividadID = Objects.requireNonNull(actividadID, "El ID de actividad no puede ser nulo");
        this.actividadPlanDeVIaje = Objects.requireNonNull(actividadPlanDeVIaje, "La actividad del plan de viaje no puede ser nulo");
    }

    public PlanDeViajeID getPlanDeViajeID() {
        return planDeViajeID;
    }

    public ActividadID getActividadID() {
        return actividadID;
    }

    public ActividadPlanDeVIaje getActividadPlanDeVIaje() {
        return actividadPlanDeVIaje;
    }
}
