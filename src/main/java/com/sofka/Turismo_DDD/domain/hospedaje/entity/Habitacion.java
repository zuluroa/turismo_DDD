package com.sofka.Turismo_DDD.domain.hospedaje.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HabitacionID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioHabitacion;
import com.sofka.Turismo_DDD.domain.hospedaje.values.TipoDeHabitacion;

import java.util.Objects;

public class Habitacion extends Entity<HabitacionID> {
    private final HabitacionID entityId;
    private  CupoMaximo cupoMaximo;
    private  TipoDeHabitacion tipoDeHabitacion;
    private  PrecioHabitacion precioHabitacion;

    public Habitacion(HabitacionID entityId, CupoMaximo cupoMaximo, TipoDeHabitacion tipoDeHabitacion, PrecioHabitacion precioHabitacion) {
        super(entityId);
        this.entityId = Objects.requireNonNull(entityId, "El ID de la habitacion no puede ser nula");
        this.cupoMaximo = Objects.requireNonNull(cupoMaximo, "El cupo maximo  no puede ser nulo");
        this.tipoDeHabitacion = Objects.requireNonNull(tipoDeHabitacion, "El tipo de la habitacion no puede ser nulo");
        this.precioHabitacion = Objects.requireNonNull(precioHabitacion, "El precio de la habitacion no puede ser nulo");
        if(precioHabitacion.value() < 0) throw new IllegalArgumentException("El precio de la habitacion debe ser mayor que 0");
    }

    public void ActualizarTipoDeHabitacion(TipoDeHabitacion tipoDeHabitacion){
        this.tipoDeHabitacion = Objects.requireNonNull(tipoDeHabitacion, "El tipo de la habitacion no puede ser nulo");
    }

    public void ActualizarCupoMaximo(CupoMaximo cupoMaximo){
        this.cupoMaximo = Objects.requireNonNull(cupoMaximo, "El cupo maximo  no puede ser nulo");
    }

    public void ActualizarPrecioHabitacion(PrecioHabitacion precioHabitacion){
        this.precioHabitacion = Objects.requireNonNull(precioHabitacion, "El precio de la habitacion no puede ser nulo");
        if(precioHabitacion.value() < 0) throw new IllegalArgumentException("El precio de la habitacion debe ser mayor que 0");
    }

    public HabitacionID getEntityId() {
        return entityId;
    }

    public CupoMaximo getCupoMaximo() {
        return cupoMaximo;
    }

    public TipoDeHabitacion getTipoDeHabitacion() {
        return tipoDeHabitacion;
    }

    public PrecioHabitacion getPrecioHabitacion() {
        return precioHabitacion;
    }
}
