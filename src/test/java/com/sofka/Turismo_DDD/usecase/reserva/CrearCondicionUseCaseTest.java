package com.sofka.Turismo_DDD.usecase.reserva;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.*;
import com.sofka.Turismo_DDD.domain.reserva.commands.CrearCondicionCommand;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.events.CondicionCreada;
import com.sofka.Turismo_DDD.domain.reserva.events.ReservaCreada;
import com.sofka.Turismo_DDD.domain.reserva.values.CondicionID;
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
class CrearCondicionUseCaseTest {


    @Mock
    DomainEventRepository repository;

    @Test
    void CrearCondicion() {

        //Arrange
        ReservaID reservaID = ReservaID.of("1234");
        CondicionID condicionID = CondicionID.of("1234");
        Observacion observacion = new Observacion("SE CREAR UNA OBSERVACION DE PRUEBA");
        var command = new CrearCondicionCommand(reservaID, condicionID, observacion);
        var usecase = new CrearCondicionUseCase();

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
        var event = (CondicionCreada) evens.get(0);
        Assertions.assertEquals(observacion, event.getObservacion());
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
    void CrearCondicion_FallaPorTopeCondicionesAgregadas() {

        //Arrange
        ReservaID reservaID = ReservaID.of("1234");
        CondicionID condicionID = CondicionID.of("1234");
        Observacion observacion = new Observacion("SE CREAR UNA OBSERVACION DE PRUEBA");
        var command = new CrearCondicionCommand(reservaID, condicionID, observacion);
        var usecase = new CrearCondicionUseCase();

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

        return List.of(new ReservaCreada(
                        new Vendedor(VendedorID.of("1234"),
                                new Identificacion("CEDULA", "1002201980"),
                                new Nombre("David", "Zuluaga"),
                                new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                                new Contacto("3202040834", "zuluroa@gmail.com")),
                        new Fecha(new Date(2022, 02, 02, 06, 30))
                ),
                new CondicionCreada(
                        CondicionID.of("1"),
                        new Observacion("OBSERVACION 1")
                ),
                new CondicionCreada(
                        CondicionID.of("2"),
                        new Observacion("OBSERVACION 2")
                ),
                new CondicionCreada(
                        CondicionID.of("3"),
                        new Observacion("OBSERVACION 3")
                ),
                new CondicionCreada(
                        CondicionID.of("4"),
                        new Observacion("OBSERVACION 4")
                ),
                new CondicionCreada(
                        CondicionID.of("5"),
                        new Observacion("OBSERVACION 5")
                ),
                new CondicionCreada(
                        CondicionID.of("6"),
                        new Observacion("OBSERVACION 6")
                )
        );
    }

}