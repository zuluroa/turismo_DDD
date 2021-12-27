package com.sofka.Turismo_DDD.domain.plandeviaje.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;

import java.util.Objects;

public class CrearPlanDeViajeCommand extends Command {
    private final PlanDeViajeID planDeViajeID;
    private final FechaPlanDeViaje fechaPlanDeViaje;
    private final CupoMaximo cupoMaximo;

    public CrearPlanDeViajeCommand(PlanDeViajeID planDeViajeID, FechaPlanDeViaje fechaPlanDeViaje, CupoMaximo cupoMaximo) {
        this.planDeViajeID = Objects.requireNonNull(planDeViajeID, "El ID del plan de viaje no puede ser nulo");
        this.fechaPlanDeViaje = Objects.requireNonNull(fechaPlanDeViaje, "La fecha del plan de viaje no puede ser nulo");
        this.cupoMaximo = Objects.requireNonNull(cupoMaximo, "La cupo maximo del plan de viaje no puede ser nulo");
    }

    public PlanDeViajeID getPlanDeViajeID() {
        return planDeViajeID;
    }

    public FechaPlanDeViaje getFechaPlanDeViaje() {
        return fechaPlanDeViaje;
    }

    public CupoMaximo getCupoMaximo() {
        return cupoMaximo;
    }
}
