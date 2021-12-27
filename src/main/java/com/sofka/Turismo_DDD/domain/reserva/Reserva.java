package com.sofka.Turismo_DDD.domain.reserva;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.*;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;
import com.sofka.Turismo_DDD.domain.reserva.entity.Cliente;
import com.sofka.Turismo_DDD.domain.reserva.entity.Condicion;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.events.*;
import com.sofka.Turismo_DDD.domain.reserva.values.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Reserva extends AggregateEvent<ReservaID> {

    protected Reserva reserva;
    protected PlanDeViajeID planDeViajeID;
    protected Fecha fecha;
    protected Vendedor vendedor;
    protected Set<Cliente> cliente;
    protected Set<Condicion> condiciones;

    public Reserva(ReservaID reservaID) {
        super(reservaID);
        subscribe( new ReservaChange(this));
    }

    public Reserva(ReservaID entityId,Vendedor vendedor, Fecha fecha) {
        super(entityId);
        subscribe( new ReservaChange(this));
        appendChange(new ReservaCreada(vendedor,fecha)).apply();
    }

    public static Reserva from(ReservaID reservaID, List<DomainEvent> events){
        var reserva = new Reserva(reservaID);
        events.forEach(reserva::applyEvent);
        return reserva;
    }

    public void agregarCliente(ClienteID clienteID,Identificacion identificacion, Nombre nombre, Direccion direccion, Contacto contacto){
        appendChange(new ClienteCreado(clienteID,identificacion,nombre,direccion,contacto)).apply();
    }

    public void crearCondicion(CondicionID condicionID,Observacion observacion){
        appendChange(new CondicionCreada(condicionID,observacion)).apply();
    }

    public void asociarVendedor(Vendedor vendedor){
        appendChange(new VendedorAsociado(vendedor)).apply();
    }

    public void asociarPlanDeViaje(PlanDeViajeID planDeViajeID){
        appendChange(new PlanDeViajeAsociado(planDeViajeID)).apply();
    }

    public void actualizarFecha(Reserva reserva, Fecha fecha){
        appendChange(new FechaActualizada(reserva,fecha)).apply();
    }

    public void cambiarVendedor(Vendedor vendedor){
        appendChange(new VendedorCambiado(vendedor)).apply();
    }

    public void actualizarContactoDeCliente(ClienteID clienteID, Contacto contacto){
        appendChange(new ClienteActualizado(clienteID,contacto)).apply();
    }


    public void actualizarNombreDeVendedor(Vendedor vendedor, Nombre nombre){
        appendChange(new VendedorActualizado(vendedor,nombre)).apply();
    }

    public void actualizarObservacionDeCondicion(CondicionID condicionID, Observacion observacion){
        appendChange(new CondicionActualizada(condicionID,observacion)).apply();
    }


    protected Optional<Cliente> getClienteById(ClienteID clienteID){
        return cliente
                .stream()
                .filter(cliente -> cliente.identity().equals(clienteID))
                .findFirst();
    }
    protected Optional<Condicion> getCondicionById(CondicionID condicionID){
        return condiciones
                .stream()
                .filter(condicion -> condicion.identity().equals(condicionID))
                .findFirst();
    }



    public Reserva getReserva() {
        return reserva;
    }

    public PlanDeViajeID getPlanDeViajeID() {
        return planDeViajeID;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Set<Cliente> getCliente() {
        return cliente;
    }

    public Set<Condicion> getCondiciones() {
        return condiciones;
    }
}
