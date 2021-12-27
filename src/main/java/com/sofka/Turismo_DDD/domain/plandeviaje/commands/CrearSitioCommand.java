package com.sofka.Turismo_DDD.domain.plandeviaje.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.SitioID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.SitioPlanDeVIaje;

import java.util.Objects;

public class CrearSitioCommand extends Command {

    private final PlanDeViajeID planDeViajeID;
    private final SitioID sitioID;
    private final SitioPlanDeVIaje sitio;

    public CrearSitioCommand(PlanDeViajeID planDeViajeID,SitioID sitioID, SitioPlanDeVIaje sitio) {
        this.planDeViajeID = Objects.requireNonNull(planDeViajeID, "El ID de plan de viaje no puede ser nulo");
        this.sitioID = Objects.requireNonNull(sitioID, "El ID de sitio no puede ser nulo");
        this.sitio = Objects.requireNonNull(sitio, "El sitio del plan de viaje no puede ser nulo");
    }

    public PlanDeViajeID getPlanDeViajeID() {
        return planDeViajeID;
    }

    public SitioID getSitioID() {
        return sitioID;
    }

    public SitioPlanDeVIaje getSitio() {
        return sitio;
    }
}
