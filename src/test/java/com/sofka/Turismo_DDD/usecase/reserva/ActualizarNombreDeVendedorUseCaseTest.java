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
import com.sofka.Turismo_DDD.domain.reserva.commands.ActualizarNombreDeVendedorCommand;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.events.ReservaCreada;
import com.sofka.Turismo_DDD.domain.reserva.events.VendedorActualizado;
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
class ActualizarNombreDeVendedorUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void ActualizarNombreDeVendedor() {
        ReservaID reservaID = ReservaID.of("1234");
        Vendedor vendedor = new Vendedor(VendedorID.of("1234"),
                new Identificacion("CEDULA", "1002201980"),
                new Nombre("David", "Zuluaga"),
                new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                new Contacto("3202040834", "zuluroa@gmail.com"));
        Nombre nombre = new Nombre("Jesus", "Zuluaga RoA");
        var command = new ActualizarNombreDeVendedorCommand(reservaID, vendedor, nombre);
        var usecase = new ActualizarNombreDeVendedorUseCase();

        when(repository.getEventsBy("1234")).thenReturn(events());
        usecase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(reservaID.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        VendedorActualizado event = (VendedorActualizado) events.getDomainEvents().get(0);
        Assertions.assertEquals("1234", event.aggregateRootId());
        Assertions.assertEquals(vendedor, event.getVendedorID());
        Assertions.assertEquals(nombre, event.getNombre());
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

    private List<DomainEvent> eventsFallo() {
        VendedorID vendedorID = VendedorID.of("1234");
        return List.of(new ReservaCreada(
                new Vendedor(vendedorID,
                        new Identificacion("CEDULA", "1002201980"),
                        new Nombre("NO TIENE", "NO TIENE"),
                        new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                        new Contacto("3202040834", "zuluroa@gmail.com")),
                new Fecha(new Date(2022, 02, 02, 06, 30))
        ));
    }

    @Test
    void ActualizarNombreDeVendedor_FallaVendedorNoTieneNombre() {
        ReservaID reservaID = ReservaID.of("xxxx");
        Vendedor vendedor = new Vendedor(VendedorID.of("1234"),
                new Identificacion("CEDULA", "1002201980"),
                new Nombre("NO TIENE", "NO TIENE"),
                new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                new Contacto("3202040834", "zuluroa@gmail.com"));
        Nombre nombre = new Nombre("Jesus", "Zuluaga RoA");
        var command = new ActualizarNombreDeVendedorCommand(reservaID, vendedor, nombre);
        var usecase = new ActualizarNombreDeVendedorUseCase();

        when(repository.getEventsBy("xxxx")).thenReturn(eventsFallo());
        usecase.addRepository(repository);

        var message = Assertions.assertThrows(BusinessException.class, () -> {
            var events = UseCaseHandler.getInstance()
                    .setIdentifyExecutor(reservaID.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();


        Assertions.assertEquals("El vendedor debe tener un nombre", message);
    }
}