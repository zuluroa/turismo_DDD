package com.sofka.Turismo_DDD.domain.hospedaje;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Habitacion;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Servicio;
import com.sofka.Turismo_DDD.domain.hospedaje.events.*;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioTotalHospedaje;

import java.util.HashSet;

public class HospedajeChange extends EventChange {
    public HospedajeChange(Hospedaje hospedaje) {
        apply((HospedajeCreado event) ->{
            hospedaje.servicios = new HashSet<>();
            hospedaje.habitaciones = new HashSet<>();
            hospedaje.localizacion = event.getLocalizacion();
            hospedaje.precioTotalHabitacion = event.getPrecioTotalHabitacion();
        });

        apply((LocalizacionCreada event) ->{
            hospedaje.localizacion = event.getLocalizacionID();
        });

        apply((CiudadDeLocalizacionActulizada event) ->{
            var localizacion = hospedaje.localizacion = event.getLocalizacion();
            localizacion.actualizarCiudad(event.getCiudad());
        });

        apply((HabitacionCreada event) ->{
            hospedaje.habitaciones.add(
                    new Habitacion(event.getHabitacionID(),
                            event.getCupoMaximo(),
                            event.getTipoDeHabitacion(),
                            event.getPrecioHabitacion())
            );
        });

        apply((PrecioHabitacionDeHabitacionActualizado event) ->{
            var habitacion = hospedaje.getHabitacionById(event.getHabitacionID())
                    .orElseThrow(() -> new IllegalArgumentException("No se encuentra la habitacion"));
            habitacion.ActualizarPrecioHabitacion(event.getPrecioHabitacion());
        });

        apply((TipoDeHabitacionDeHabitacionActualizado event) ->{
            var habitacion = hospedaje.getHabitacionById(event.getHabitacionID())
                    .orElseThrow(() -> new IllegalArgumentException("No se encuentra la habitacion"));
            habitacion.ActualizarTipoDeHabitacion(event.getTipoDeHabitacion());
        });

        apply((CupoMaximoDeHabitacionDeHabitacionActualizado event) ->{
            var habitacion = hospedaje.getHabitacionById(event.getHabitacionID())
                    .orElseThrow(() -> new IllegalArgumentException("No se encuentra la habitacion"));
            habitacion.ActualizarCupoMaximo(event.getCupoMaximo());
        });

        apply((HabitacionQuitada event) ->{
            var Id = event.getHabitacionID();
            hospedaje.habitaciones.removeIf(habitacion -> habitacion.identity().equals(Id));
        });

        apply((ServicioCreado event) ->{
            hospedaje.servicios.add(
                    new Servicio(event.getServicioID(),
                            event.getTipoDeServicio())
            );
        });

        apply((ServicioQuitado event) ->{
            var Id = event.getServicioID();
            hospedaje.servicios.removeIf(servicio -> servicio.identity().equals(Id));
        });

        apply((TipoDeServicioDeServicioActualizado event) ->{
            var habitacion = hospedaje.getServicioById(event.getServicio().getEntityId())
                    .orElseThrow(() -> new IllegalArgumentException("No se encuentra el servicio"));
            habitacion.ActualizarTipoDeServicio(event.getTipoDeServicio());
        });

        apply((PrecioTotalHospedajeActulizado event) ->{
            hospedaje.precioTotalHabitacion = event.getPrecioTotalHabitacion();
        });
    }
}
