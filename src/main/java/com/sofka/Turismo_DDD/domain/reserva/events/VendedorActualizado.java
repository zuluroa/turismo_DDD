package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;

import java.util.Objects;

public class VendedorActualizado extends DomainEvent {
    private final Vendedor vendedor;
    private final Nombre nombre;

    public VendedorActualizado(Vendedor vendedor, Nombre nombre) {
        super("sofka.reserva.vendedoractualizado");
        this.vendedor = Objects.requireNonNull(vendedor, "El ID de vendedor no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre del vendedor no puede ser nulo");
    }

    public Vendedor getVendedorID() {
        return vendedor;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
