package com.sofka.Turismo_DDD.domain.plandeviaje;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.hospedaje.Hospedaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.Actividad;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.Sitio;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.*;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PlanDeViaje extends AggregateEvent<PlanDeViajeID> {

    protected Set<Actividad> actividades;
    protected Set<Sitio> sitios;
    protected FechaPlanDeViaje fechaPlanDeViaje;
    protected Hospedaje hospedaje;
    protected CupoMaximo cupoMaximo;
    protected PlanDeViaje planDeViaje;

    public PlanDeViaje(PlanDeViajeID planDeViajeID){
        super(planDeViajeID);
        subscribe(new PlanDeViajeChange(this));
    }

    public PlanDeViaje(PlanDeViajeID entityId,FechaPlanDeViaje fechaPlanDeViaje,CupoMaximo cupoMaximo) {
        super(entityId);
        subscribe(new PlanDeViajeChange(this));
        appendChange(new PlanDeViajeCreado(fechaPlanDeViaje,cupoMaximo)).apply();
    }

    public static PlanDeViaje from(PlanDeViajeID planDeViajeID, List<DomainEvent> events){
        var planDeViaje = new PlanDeViaje(planDeViajeID);
        events.forEach(planDeViaje::applyEvent);
        return planDeViaje;
    }

    public void asociarHospedaje(Hospedaje hospedaje){
        appendChange(new HospedajeAsociado(hospedaje)).apply();
    }

    public void crearFechaPlanDeViaje(FechaPlanDeViaje fechaPlanDeViaje){
        appendChange(new FechaPlanDeViajeCreada(fechaPlanDeViaje)).apply();
    }

    public void actualizarFechaIdaDeFechaPlanDeViaje(FechaPlanDeViaje fechaPlanDeViaje, FechaIda fechaIda){
        appendChange(new FechaIdaDeFechaPlanDeViajeActualizada(fechaPlanDeViaje,fechaIda)).apply();
    }

    public void ActualizarFechaRegresoDeFechaPlanDeViaje(FechaPlanDeViaje fechaPlanDeViaje, FechaRegreso fechaRegreso){
        appendChange(new FechaRegresoDeFechaPlanDeViajeActualizada(fechaPlanDeViaje,fechaRegreso)).apply();
    }

    public void crearSitio(SitioID sitioID, SitioPlanDeVIaje sitio){
        appendChange(new SitioCreado(sitioID,sitio)).apply();
    }

    public void ActualizarSitioPlanDeViajeDeSitio(Sitio sitio, SitioPlanDeVIaje sitioPlanDeVIaje){
        appendChange(new SitioPlanDeViajeActualizado(sitio,sitioPlanDeVIaje)).apply();
    }

    public void QuitarSitioPlanDeViajeDeSitio(SitioID sitioID){
        appendChange(new SitioPlanDeViajeQuitado(sitioID)).apply();
    }

    public void crearActividad(ActividadID actividadID, ActividadPlanDeVIaje actividadPlanDeVIaje){
        appendChange(new ActividadCreado(actividadID,actividadPlanDeVIaje)).apply();
    }

    public void ActualizarActividadPlanDeViajeDeActividad(Actividad actividad, ActividadPlanDeVIaje actividadPlanDeVIaje){
        appendChange(new ActividadPlanDeViajeActualizado(actividad,actividadPlanDeVIaje)).apply();
    }

    public void QuitarActividadPlanDeViajeDeActividad(ActividadID actividadID){
        appendChange(new ActividadPlanDeViajeQuitado(actividadID)).apply();
    }

    public void ActualizarCupoMaximo(PlanDeViaje planDeViaje,CupoMaximo cupoMaximo){
        appendChange(new CupoMaximoActualizado(planDeViaje,cupoMaximo)).apply();
    }

    protected Optional<Actividad> getActividadById(ActividadID actividadID){
        return actividades
                .stream()
                .filter(actividad -> actividad.identity().equals(actividadID))
                .findFirst();
    }

    protected Optional<Sitio> getSitioById(SitioID sitioID){
        return sitios
                .stream()
                .filter(sitio -> sitio.identity().equals(sitioID))
                .findFirst();
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public Set<Sitio> getSitios() {
        return sitios;
    }

    public FechaPlanDeViaje getFechaPlanDeViaje() {
        return fechaPlanDeViaje;
    }

    public Hospedaje getHospedaje() {
        return hospedaje;
    }

    public CupoMaximo getCupoMaximo() {
        return cupoMaximo;
    }

    public PlanDeViaje getPlanDeViaje() {
        return planDeViaje;
    }
}
