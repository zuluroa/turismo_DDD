package com.sofka.Turismo_DDD.domain.reserva.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.genericvalues.Observacion;
import com.sofka.Turismo_DDD.domain.reserva.values.CondicionID;
import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;

import java.util.Objects;

public class CrearCondicionCommand extends Command {
    private ReservaID reservaID;
    private final CondicionID condicionID;
    private final Observacion observacion;

    public CrearCondicionCommand(ReservaID reservaID,CondicionID condicionID, Observacion observacion) {
        this.reservaID = Objects.requireNonNull(reservaID,"");
        this.condicionID = Objects.requireNonNull(condicionID, "El ID de la condicion no puede ser nula");
        this.observacion = Objects.requireNonNull(observacion,"La observacion no puede ser nula");
    }

    public ReservaID getReservaID() {
        return reservaID;
    }

    public CondicionID getCondicionID() {
        return condicionID;
    }

    public Observacion getObservacion() {
        return observacion;
    }
}
