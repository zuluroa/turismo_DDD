package com.sofka.Turismo_DDD.domain.reserva.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.values.Fecha;
import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;

import java.util.Objects;

public class CrearReservaCommand extends Command {
    private final ReservaID reservaID;
    private final Vendedor vendedor;
    private final Fecha fecha;

    public CrearReservaCommand(ReservaID reservaID, Vendedor vendedor, Fecha fecha) {
        this.reservaID = Objects.requireNonNull(reservaID, "El ID de reserva no puede ser nulo");
        this.vendedor = Objects.requireNonNull(vendedor, "El vendedor no puede ser nulo");
        this.fecha = Objects.requireNonNull(fecha, "La fecha no puede ser nula");
    }

    public ReservaID getReservaID() {
        return reservaID;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
