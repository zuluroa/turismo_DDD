package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;

import java.util.Objects;

public class VendedorAsociado extends DomainEvent {
    private final Vendedor vendedor;

    public VendedorAsociado(Vendedor vendedor) {
        super("sofka.reserva.vendedorcreado");
        this.vendedor = Objects.requireNonNull(vendedor, "El  vendedor no puede ser nulo");
    }

    public Vendedor getVendedorID() {
        return vendedor;
    }
}
