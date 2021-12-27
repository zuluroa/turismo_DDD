package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HabitacionID;

import java.util.Objects;

public class HabitacionQuitada extends DomainEvent {
    private final HabitacionID habitacionID;

    public HabitacionQuitada(HabitacionID habitacionID) {
        super("sofka.hospedaje.habitacionquitada");
        this.habitacionID = Objects.requireNonNull(habitacionID, "El ID de la habitacion no puede ser nula");
    }

    public HabitacionID getHabitacionID() {
        return habitacionID;
    }
}
