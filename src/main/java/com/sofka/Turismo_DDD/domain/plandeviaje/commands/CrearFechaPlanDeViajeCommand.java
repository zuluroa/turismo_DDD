package com.sofka.Turismo_DDD.domain.plandeviaje.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;

import java.util.Objects;

public class CrearFechaPlanDeViajeCommand extends Command {
    private final PlanDeViajeID planDeViajeID;
    private final FechaPlanDeViaje fechaPlanDeViaje;

    public CrearFechaPlanDeViajeCommand(PlanDeViajeID planDeViajeID,FechaPlanDeViaje fechaPlanDeViaje) {
        this.planDeViajeID = Objects.requireNonNull(planDeViajeID, "El ID de plan de viaje no puede ser nulo");
        this.fechaPlanDeViaje = Objects.requireNonNull(fechaPlanDeViaje, "La Fecha de plan de viaje no puede ser nula");
    }

    public PlanDeViajeID getPlanDeViajeID() {
        return planDeViajeID;
    }

    public FechaPlanDeViaje getFechaPlanDeViaje() {
        return fechaPlanDeViaje;
    }
}
