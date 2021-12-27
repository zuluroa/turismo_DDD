package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearFechaPlanDeViajeCommand;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.FechaPlanDeViajeCreada;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.PlanDeViajeCreado;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaIda;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaPlanDeViajeID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaRegreso;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearFechaPlanDeViajeUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void CrearFechaPlanDeViaje() {
        PlanDeViajeID planDeViajeID = PlanDeViajeID.of("1234");
        FechaPlanDeViajeID fechaPlanDeViajeID = FechaPlanDeViajeID.of("xxxx");

        var fechaIda = Calendar.getInstance();
        fechaIda.set(2022, Calendar.JANUARY, 3);

        var fechaRegreso = Calendar.getInstance();
        fechaRegreso.set(2022, Calendar.JANUARY, 4);

        FechaPlanDeViaje fechaPlanDeViaje = new FechaPlanDeViaje(fechaPlanDeViajeID, new FechaIda(fechaIda.getTime()), new FechaRegreso(fechaRegreso.getTime()));

        var command = new CrearFechaPlanDeViajeCommand(planDeViajeID, fechaPlanDeViaje);
        var usecase = new CrearFechaPlanDeViajeUseCase();

        when(repository.getEventsBy("1234")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var evens = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(planDeViajeID.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //Assert
        var event = (FechaPlanDeViajeCreada) evens.get(0);
        Assertions.assertEquals(fechaPlanDeViaje, event.getFechaPlanDeViaje());
        Mockito.verify(repository).getEventsBy("1234");
    }

    private List<DomainEvent> events() {
        var fechaIda = Calendar.getInstance();
        fechaIda.set(2022, Calendar.JANUARY, 3);

        var fechaRegreso = Calendar.getInstance();
        fechaRegreso.set(2022, Calendar.JANUARY, 4);
        FechaPlanDeViajeID fechaPlanDeViajeID = FechaPlanDeViajeID.of("yyyyy");
        return List.of(new PlanDeViajeCreado(new FechaPlanDeViaje(fechaPlanDeViajeID, new FechaIda(fechaIda.getTime()), new FechaRegreso(fechaRegreso.getTime())),
                new CupoMaximo(2)
        ));
    }

    @Test
    void CrearFechaPlanDeViajeFallaPorDiaNoHabil() {
        PlanDeViajeID planDeViajeID = PlanDeViajeID.of("1234");
        FechaPlanDeViajeID fechaPlanDeViajeID = FechaPlanDeViajeID.of("xxxx");

        var fechaIda = Calendar.getInstance();
        fechaIda.set(2022, Calendar.JANUARY, 25);

        var FechaRegreso = Calendar.getInstance();
        FechaRegreso.set(2022, Calendar.JANUARY, 29);

        FechaPlanDeViaje fechaPlanDeViaje = new FechaPlanDeViaje(fechaPlanDeViajeID, new FechaIda(fechaIda.getTime()), new FechaRegreso(FechaRegreso.getTime()));

        var command = new CrearFechaPlanDeViajeCommand(planDeViajeID, fechaPlanDeViaje);
        var usecase = new CrearFechaPlanDeViajeUseCase();

        when(repository.getEventsBy("1234")).thenReturn(eventsFallo());
        usecase.addRepository(repository);

        var message = Assertions.assertThrows(BusinessException.class, () -> {
            var events = UseCaseHandler.getInstance()
                    .setIdentifyExecutor(planDeViajeID.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();

        Assertions.assertEquals("No puede crear fechas en fines de semana", message);
    }

    private List<DomainEvent> eventsFallo() {
        var fechaIda = Calendar.getInstance();
        fechaIda.set(2022, Calendar.DECEMBER, 25);

        var FechaRegreso = Calendar.getInstance();
        FechaRegreso.set(2022, Calendar.DECEMBER, 29);

        FechaPlanDeViajeID fechaPlanDeViajeID = FechaPlanDeViajeID.of("yyyyy");
        return List.of(new PlanDeViajeCreado(new FechaPlanDeViaje(fechaPlanDeViajeID, new FechaIda(fechaIda.getTime()), new FechaRegreso(FechaRegreso.getTime())),
                new CupoMaximo(2)
        ));
    }

}