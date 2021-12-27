package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HabitacionID;

import java.util.Objects;

public class CupoMaximoDeHabitacionDeHabitacionActualizado extends DomainEvent {
    private final HabitacionID habitacionID;
    private final CupoMaximo cupoMaximo;

    public CupoMaximoDeHabitacionDeHabitacionActualizado(HabitacionID habitacionID, CupoMaximo cupoMaximo) {
        super("sofka.hospedaje.cupomaximodehabitaciondehabitacionactualizado");
        this.habitacionID = Objects.requireNonNull(habitacionID, "El ID de la habitacion no puede ser nula");
        this.cupoMaximo = Objects.requireNonNull(cupoMaximo, "El cupo maximo no puede ser nulo");
    }

    public HabitacionID getHabitacionID() {
        return habitacionID;
    }

    public CupoMaximo getCupoMaximo() {
        return cupoMaximo;
    }
}
