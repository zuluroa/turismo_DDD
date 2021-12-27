package com.sofka.Turismo_DDD.domain.plandeviaje.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaIda;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaPlanDeViajeID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaRegreso;

import java.util.Objects;

public class FechaPlanDeViaje extends Entity<FechaPlanDeViajeID> {

    private final FechaPlanDeViajeID planDeViajeID;
    private FechaIda fechaIda;
    private  FechaRegreso fechaRegreso;

    public FechaPlanDeViaje(FechaPlanDeViajeID planDeViajeID, FechaIda fechaIda, FechaRegreso fechaRegreso) {
        super(planDeViajeID);
        this.planDeViajeID = Objects.requireNonNull(planDeViajeID,"El ID de fecha plan de viaje no puede ser nula");
        this.fechaIda = Objects.requireNonNull(fechaIda, "La fecha de ida no puede ser nula");
        this.fechaRegreso = Objects.requireNonNull(fechaRegreso, "La fecha de regreso no puede ser nula");
    }

    public void ActualizarFechaIda(FechaIda fechaIda){
        this.fechaIda = Objects.requireNonNull(fechaIda, "La fecha de ida no puede ser nula");
    }

    public void ActualizarFechaRegreso(FechaRegreso fechaRegreso){
        this.fechaRegreso = Objects.requireNonNull(fechaRegreso, "La fecha de regreso no puede ser nula");
    }

    public FechaPlanDeViajeID getPlanDeViajeID() {
        return planDeViajeID;
    }

    public FechaIda getFechaIda() {
        return fechaIda;
    }

    public FechaRegreso getFechaRegreso() {
        return fechaRegreso;
    }
}
