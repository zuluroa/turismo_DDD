package com.sofka.Turismo_DDD.domain.hospedaje;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Habitacion;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Servicio;
import com.sofka.Turismo_DDD.domain.hospedaje.events.*;
import com.sofka.Turismo_DDD.domain.hospedaje.values.*;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Hospedaje extends AggregateEvent<HospedajeID> {

    protected Localizacion localizacion;
    protected Set<Habitacion> habitaciones;
    protected Set<Servicio> servicios;
    protected PrecioTotalHospedaje precioTotalHabitacion;

    public Hospedaje(HospedajeID entityId){
        super(entityId);
        subscribe( new HospedajeChange(this));
    }

    public Hospedaje(HospedajeID entityId,Localizacion localizacion, PrecioTotalHospedaje precioTotalHabitacion) {
        super(entityId);
        subscribe( new HospedajeChange(this));
        appendChange(new HospedajeCreado(localizacion,precioTotalHabitacion)).apply();
    }

    public static Hospedaje from(HospedajeID hospedajeID, List<DomainEvent> events){
        var hospedaje = new Hospedaje(hospedajeID);
        events.forEach(hospedaje::applyEvent);
        return hospedaje;
    }

    public void crearLocalizacion(Localizacion localizacion){
        appendChange(new LocalizacionCreada(localizacion)).apply();
    }

    public void actualizarCiudadDeLocalizacion(Localizacion localizacion, Ciudad ciudad){
        appendChange(new CiudadDeLocalizacionActulizada(localizacion,ciudad)).apply();
    }

    public void crearHabitacion(HabitacionID habitacionID, CupoMaximo cupoMaximo, TipoDeHabitacion tipoDeHabitacion, PrecioHabitacion precioHabitacion){
        appendChange(new HabitacionCreada(habitacionID,cupoMaximo,tipoDeHabitacion,precioHabitacion)).apply();
    }

    public void actualizarPrecioHabitacionDeHabitacion(HabitacionID habitacionID,PrecioHabitacion precioHabitacion){
        appendChange(new PrecioHabitacionDeHabitacionActualizado(habitacionID,precioHabitacion)).apply();
    }

    public void actualizarTipoDeHabitacionDeHabitacion(HabitacionID habitacionID,TipoDeHabitacion tipoDeHabitacion){
        appendChange(new TipoDeHabitacionDeHabitacionActualizado(habitacionID,tipoDeHabitacion)).apply();
    }

    public void actualizarCupoMaximoDeHabitacion(HabitacionID habitacionID,CupoMaximo cupoMaximo){
        appendChange(new CupoMaximoDeHabitacionDeHabitacionActualizado(habitacionID,cupoMaximo)).apply();
    }

    public void QuitarHabitacion(HabitacionID habitacionID){
        appendChange(new HabitacionQuitada(habitacionID)).apply();
    }

    public void crearServicio(ServicioID servicioID, TipoDeServicio tipoDeServicio){
        appendChange(new ServicioCreado(servicioID,tipoDeServicio)).apply();
    }

    public void ActualizarPrecioTotalHospedaje(Hospedaje hospedaje, PrecioTotalHospedaje precioTotalHabitacion){
        appendChange(new PrecioTotalHospedajeActulizado(hospedaje,precioTotalHabitacion)).apply();
    }

    public void quitarServicio(ServicioID servicioID){
        appendChange(new ServicioQuitado(servicioID)).apply();
    }

    public void ActualizarTipoDeServicioDeServicio(Servicio servicio,TipoDeServicio tipoDeServicio){
        appendChange(new TipoDeServicioDeServicioActualizado(servicio,tipoDeServicio)).apply();
    }

    protected Optional<Habitacion> getHabitacionById(HabitacionID habitacionID){
        return habitaciones
                .stream()
                .filter(habitacion -> habitacion.identity().equals(habitacionID))
                .findFirst();
    }

    protected Optional<Servicio> getServicioById(ServicioID servicioID){
        return servicios
                .stream()
                .filter(servicio -> servicio.identity().equals(servicioID))
                .findFirst();
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public Set<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Set<Servicio> getServicios() {
        return servicios;
    }

    public PrecioTotalHospedaje getPrecioTotalHabitacion() {
        return precioTotalHabitacion;
    }

    @Override
    public String toString() {
        return "Hospedaje{" +
                "localizacion=" + localizacion +
                ", habitaciones=" + habitaciones +
                ", servicios=" + servicios +
                ", precioTotalHabitacion=" + precioTotalHabitacion +
                '}';
    }
}
