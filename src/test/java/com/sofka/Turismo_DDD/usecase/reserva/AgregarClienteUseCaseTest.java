package com.sofka.Turismo_DDD.usecase.reserva;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.Contacto;
import com.sofka.Turismo_DDD.domain.genericvalues.Direccion;
import com.sofka.Turismo_DDD.domain.genericvalues.Identificacion;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.commands.AgregarClienteCommand;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.events.ClienteCreado;
import com.sofka.Turismo_DDD.domain.reserva.events.ReservaCreada;
import com.sofka.Turismo_DDD.domain.reserva.values.ClienteID;
import com.sofka.Turismo_DDD.domain.reserva.values.Fecha;
import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;
import com.sofka.Turismo_DDD.domain.reserva.values.VendedorID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AgregarClienteUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void CrearCliente() {
        ReservaID reservaID = ReservaID.of("1234");
        ClienteID clienteID = ClienteID.of("1234");
        Identificacion identificacion = new Identificacion("cedula", "1234567890");
        Nombre nombre = new Nombre("David", "Zuluaga");
        Direccion direccion = new Direccion("Cucuta", "Av 12 calle novena");
        Contacto contacto = new Contacto("32020408340", "zuluroa@gmail.com");
        var command = new AgregarClienteCommand(reservaID, clienteID,
                identificacion, nombre, direccion, contacto);
        var usecase = new AgregarClienteUseCase();

        when(repository.getEventsBy("1234")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var evens = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(reservaID.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //Assert
        var event = (ClienteCreado) evens.get(0);
        Assertions.assertEquals(clienteID, event.getClienteID());
        Assertions.assertEquals(identificacion, event.getIdentificacion());
        Assertions.assertEquals(nombre, event.getNombre());
        Assertions.assertEquals(direccion, event.getDireccion());
        Assertions.assertEquals(contacto, event.getContacto());
        Mockito.verify(repository).getEventsBy("1234");
    }

    private List<DomainEvent> events() {
        VendedorID vendedorID = VendedorID.of("1234");
        return List.of(new ReservaCreada(
                new Vendedor(vendedorID,
                        new Identificacion("CEDULA", "1002201980"),
                        new Nombre("David", "Zuluaga"),
                        new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                        new Contacto("3202040834", "zuluroa@gmail.com")),
                new Fecha(new Date(2022, 02, 02, 06, 30))
        ));
    }

    @Test
    void CrearCliente_FallaPorTopeClientesAgregadas() {

        ReservaID reservaID = ReservaID.of("1234");
        ClienteID clienteID = ClienteID.of("1234");
        Identificacion identificacion = new Identificacion("cedula", "1234567890");
        Nombre nombre = new Nombre("David", "Zuluaga");
        Direccion direccion = new Direccion("Cucuta", "Av 12 calle novena");
        Contacto contacto = new Contacto("32020408340", "zuluroa@gmail.com");
        var command = new AgregarClienteCommand(reservaID, clienteID,
                identificacion, nombre, direccion, contacto);
        var usecase = new AgregarClienteUseCase();

        when(repository.getEventsBy("1234")).thenReturn(fullICondicionesEvents());
        usecase.addRepository(repository);

        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(reservaID.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        });
    }

    private List<DomainEvent> fullICondicionesEvents() {
        Identificacion identificacion = new Identificacion("cedula", "1234567890");
        Nombre nombre = new Nombre("David", "Zuluaga");
        Direccion direccion = new Direccion("Cucuta", "Av 12 calle novena");
        Contacto contacto = new Contacto("32020408340", "zuluroa@gmail.com");
        return List.of(new ReservaCreada(
                        new Vendedor(VendedorID.of("1234"),
                                new Identificacion("CEDULA", "1002201980"),
                                new Nombre("David", "Zuluaga"),
                                new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                                new Contacto("3202040834", "zuluroa@gmail.com")),
                        new Fecha(new Date(2022, 02, 02, 06, 30))
                ),
                new ClienteCreado(ClienteID.of("1"),
                        identificacion, nombre, direccion, contacto),
                new ClienteCreado(ClienteID.of("2"),
                        identificacion, nombre, direccion, contacto),
                new ClienteCreado(ClienteID.of("3"),
                        identificacion, nombre, direccion, contacto)
        );
    }
}