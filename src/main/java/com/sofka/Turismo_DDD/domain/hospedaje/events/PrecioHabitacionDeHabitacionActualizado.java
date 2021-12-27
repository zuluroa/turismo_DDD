package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HabitacionID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioHabitacion;

import java.util.Objects;

public class PrecioHabitacionDeHabitacionActualizado extends DomainEvent {
    private final HabitacionID habitacionID;
    private final PrecioHabitacion precioHabitacion;

    public PrecioHabitacionDeHabitacionActualizado(HabitacionID habitacionID, PrecioHabitacion precioHabitacion) {
        super("sofka.hospedaje.preciohabitaciondehabitacionactualizado");
        this.habitacionID = Objects.requireNonNull(habitacionID, "El ID de la habitacion no puede ser nula");
        this.precioHabitacion = Objects.requireNonNull(precioHabitacion, "El precio de la habitacion no puede ser nulo");
        if(precioHabitacion.value().isNaN())throw new IllegalArgumentException("El precio de la habitacion debe ser de tipo número");
        if(precioHabitacion.value() > 0)throw new IllegalArgumentException("El precio debe de la habitacion ser de mayor a 0");
    }

    public HabitacionID getHabitacionID() {
        return habitacionID;
    }

    public PrecioHabitacion getPrecioHabitacion() {
        return precioHabitacion;
    }
}
