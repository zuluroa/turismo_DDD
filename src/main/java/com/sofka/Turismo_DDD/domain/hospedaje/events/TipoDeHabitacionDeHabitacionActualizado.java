package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HabitacionID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.TipoDeHabitacion;

import java.util.Objects;

public class TipoDeHabitacionDeHabitacionActualizado extends DomainEvent {
    private final HabitacionID habitacionID;
    private final TipoDeHabitacion tipoDeHabitacion;

    public TipoDeHabitacionDeHabitacionActualizado(HabitacionID habitacionID, TipoDeHabitacion tipoDeHabitacion) {
        super("sofka.hospedaje.tipodehabitaciondehabitacionactualizado");
        this.habitacionID = Objects.requireNonNull(habitacionID, "El ID de la habitacion no puede ser nula");
        this.tipoDeHabitacion = Objects.requireNonNull(tipoDeHabitacion, "El tipo de habitacion no puede ser nulo");
    }

    public HabitacionID getHabitacionID() {
        return habitacionID;
    }

    public TipoDeHabitacion getTipoDeHabitacion() {
        return tipoDeHabitacion;
    }
}
