package com.sofka.Turismo_DDD.domain.plandeviaje.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadPlanDeVIaje;

import java.util.Objects;

import static com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo.compararSiNuloOEsVacio;
import static com.sofka.Turismo_DDD.domain.utils.mayorOIgual.mayorOIgualQue;

public class Actividad extends Entity<ActividadID> {

    private final ActividadID actividadID;
    private  ActividadPlanDeVIaje actividad;

    public Actividad(ActividadID actividadID, ActividadPlanDeVIaje actividad) {
        super(actividadID);
        this.actividadID = Objects.requireNonNull(actividadID, "El ID de actividad no puede ser nulo");
        this.actividad = Objects.requireNonNull(actividad, "La actividad no puede ser nula");
        if(compararSiNuloOEsVacio(actividad.value()))throw new IllegalArgumentException("La actividad no puede ser vacio");
        if(mayorOIgualQue(actividad.value().length() , 6)) throw new IllegalArgumentException("La actividad debe ser mayor de 3 caracteres");
    }

    public void ActualizarActividad(ActividadPlanDeVIaje actividad) {
        this.actividad = Objects.requireNonNull(actividad, "La actividad del viaje no puede ser nula");
    }

    public ActividadID getActividadID() {
        return actividadID;
    }

    public ActividadPlanDeVIaje getActividad() {
        return actividad;
    }
}
