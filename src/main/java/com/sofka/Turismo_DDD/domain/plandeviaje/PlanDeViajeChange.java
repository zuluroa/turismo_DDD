package com.sofka.Turismo_DDD.domain.plandeviaje;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.Actividad;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.Sitio;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.*;

import java.util.HashSet;

public class PlanDeViajeChange extends EventChange {

    public PlanDeViajeChange(PlanDeViaje planDeViaje) {
        apply((PlanDeViajeCreado event) -> {
            planDeViaje.fechaPlanDeViaje = event.getFechaPlanDeViaje();
            planDeViaje.cupoMaximo = event.getCupoMaximo();
            planDeViaje.sitios = new HashSet<>();
            planDeViaje.actividades = new HashSet<>();
        });

        apply((HospedajeAsociado event) -> {
            planDeViaje.hospedaje = event.getHospedaje();
        });

        apply((FechaPlanDeViajeCreada event) -> {
            planDeViaje.fechaPlanDeViaje = event.getFechaPlanDeViaje();
        });

        apply((FechaIdaDeFechaPlanDeViajeActualizada event) -> {
            var fechaPlanDeViaje = planDeViaje.fechaPlanDeViaje = event.getFechaPlanDeViaje();
            fechaPlanDeViaje.ActualizarFechaIda(event.getFechaIda());
        });

        apply((FechaRegresoDeFechaPlanDeViajeActualizada event) -> {
            var fechaPlanDeViaje = planDeViaje.fechaPlanDeViaje = event.getFechaPlanDeViaje();
            fechaPlanDeViaje.ActualizarFechaRegreso(event.getFechaRegreso());
        });

        apply((SitioCreado event) -> {
            planDeViaje.sitios.add(
                    new Sitio(event.getSitioID(), event.getSitio())
            );
        });

        apply((SitioPlanDeViajeActualizado event) -> {
            var sitio = planDeViaje.getSitioById(event.getSitio().identity())
                    .orElseThrow(() -> new IllegalArgumentException("No se encuentra el sitio"));
            sitio.ActualizarSitio(event.getSitioPlanDeVIaje());
        });

        apply((SitioPlanDeViajeQuitado event) -> {
            var idSitio = event.getSitioID();
            planDeViaje.sitios.removeIf(sitio -> sitio.identity().equals(idSitio));
        });

        apply((ActividadCreado event) -> {
            planDeViaje.actividades.add(
                    new Actividad(event.getActividadID(), event.getActividadPlanDeVIaje())
            );
        });

        apply((ActividadPlanDeViajeActualizado event) -> {
            var actividad = planDeViaje.getActividadById(event.getActividad().identity())
                    .orElseThrow(() -> new IllegalArgumentException("No se encuentra la actividad"));
            actividad.ActualizarActividad(event.getActividadPlanDeVIaje());
        });

        apply((ActividadPlanDeViajeQuitado event) -> {
            var idActividad = event.getActividadID();
            planDeViaje.actividades.removeIf(actividad -> actividad.identity().equals(idActividad));
        });

        apply((CupoMaximoActualizado event) -> {
            var cupoMaximo = planDeViaje.planDeViaje = event.getPlanDeViaje();
            cupoMaximo.cupoMaximo = new CupoMaximo(event.getCupoMaximo().value());
        });
    }
}
