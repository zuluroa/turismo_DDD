package com.sofka.Turismo_DDD.domain.reserva.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;

import java.util.Objects;

public class ActualizarNombreDeVendedorCommand extends Command {


    private final ReservaID reservaID;
    private final Vendedor vendedor;
    private final Nombre nombre;

    public ActualizarNombreDeVendedorCommand(ReservaID reservaID, Vendedor vendedor, Nombre nombre) {
        this.reservaID = Objects.requireNonNull(reservaID, "El ID de reserva no puede ser nulo");
        this.vendedor = Objects.requireNonNull(vendedor, "El vendedor no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
    }

    public ReservaID getReservaID() {
        return reservaID;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
