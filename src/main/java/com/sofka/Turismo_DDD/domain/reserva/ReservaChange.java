package com.sofka.Turismo_DDD.domain.reserva;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.Turismo_DDD.domain.reserva.entity.Cliente;
import com.sofka.Turismo_DDD.domain.reserva.entity.Condicion;
import com.sofka.Turismo_DDD.domain.reserva.events.*;
import com.sofka.Turismo_DDD.domain.reserva.values.Fecha;

import java.util.HashSet;

public class ReservaChange extends EventChange {
    public ReservaChange(Reserva reserva) {
        apply((ReservaCreada event) -> {
            reserva.fecha = event.getFecha();
            reserva.cliente = new HashSet<>();
            reserva.vendedor = event.getVendedor();
            reserva.condiciones = new HashSet<>();
        });

        apply((ClienteCreado event) -> {
            reserva.cliente.add(new Cliente(
                    event.getClienteID(),
                    event.getIdentificacion(),
                    event.getNombre(),
                    event.getDireccion(),
                    event.getContacto()
            ));
        });

        apply((ClienteActualizado event) -> {
            var cliente = reserva.getClienteById(event.getClienteID())
                    .orElseThrow(() -> new IllegalArgumentException("No se encuentra el cliente"));
            cliente.actualizarContacto(event.getContacto());
        });

        apply((CondicionActualizada event) -> {
            var condicion = reserva.getCondicionById(event.getCondicionID())
                    .orElseThrow(() -> new IllegalArgumentException("No se encuentra la condicion"));
            condicion.actualizarObservacion(event.getObservacion());
        });

        apply((CondicionCreada event) -> {
            reserva.condiciones.add(
                    new Condicion(event.getCondicionID(),
                            event.getObservacion())
            );
        });

        apply((VendedorAsociado event) -> {
            reserva.vendedor = event.getVendedorID();
        });

        apply((VendedorCambiado event) -> {
            reserva.vendedor = event.getVendedor();
        });

        apply((PlanDeViajeAsociado event) -> {
            reserva.planDeViajeID = event.getPlanDeViajeID();
        });

        apply((VendedorActualizado event) -> {
            var vendedor = reserva.vendedor = event.getVendedorID();
            vendedor.actualizarNombre(event.getNombre());
        });

        apply((FechaActualizada event) -> {
            var cambiarFechaReserva = reserva.reserva = event.getReserva();
            cambiarFechaReserva.fecha = new Fecha(event.getFecha().value());
        });
    }
}
