package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearActividadCommand;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearSitioCommand;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.ActividadCreado;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.PlanDeViajeCreado;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.SitioCreado;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.*;
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
class CrearActividadUseCaseTest {
    @Mock
    DomainEventRepository repository;

    @Test
     void CrearActividad() {
        PlanDeViajeID planDeViajeID = PlanDeViajeID.of("1234");
        ActividadID actividadID = ActividadID.of("xxxx");
        ActividadPlanDeVIaje actividadPlanDeVIaje = new ActividadPlanDeVIaje("REALIZAR UN RECORRIDO A LA MONTAÑA");
        var command = new CrearActividadCommand(planDeViajeID,actividadID,actividadPlanDeVIaje);
        var usecase = new CrearActividadUseCase();

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
        var event = (ActividadCreado) evens.get(0);
        Assertions.assertEquals(actividadID, event.getActividadID());
        Assertions.assertEquals(actividadPlanDeVIaje, event.getActividadPlanDeVIaje());
        Mockito.verify(repository).getEventsBy("1234");
    }

    private List<DomainEvent> events() {
        FechaPlanDeViajeID fechaPlanDeViajeID= FechaPlanDeViajeID.of("yyyyy");
        return List.of(new PlanDeViajeCreado(new FechaPlanDeViaje(fechaPlanDeViajeID,new FechaIda(new Date()),new FechaRegreso(new Date())),
                new CupoMaximo(2)
        ));
    }

    @Test
     void CrearCondicion_FallaPorTopeCondicionesAgregadas() {
        PlanDeViajeID planDeViajeID = PlanDeViajeID.of("1234");
        ActividadID actividadID = ActividadID.of("xxxx");
        ActividadPlanDeVIaje actividadPlanDeVIaje = new ActividadPlanDeVIaje("REALIZAR UN RECORRIDO A LA MONTAÑA");
        var command = new CrearActividadCommand(planDeViajeID,actividadID,actividadPlanDeVIaje);
        var usecase = new CrearActividadUseCase();

        when(repository.getEventsBy("1234")).thenReturn(fullActividadesEvents());
        usecase.addRepository(repository);

        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler
                    .getInstance()
                    .setIdentifyExecutor(planDeViajeID.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        });
    }

    private List<DomainEvent> fullActividadesEvents() {
        FechaPlanDeViajeID fechaPlanDeViajeID= FechaPlanDeViajeID.of("yyyyy");
        return List.of(new PlanDeViajeCreado(new FechaPlanDeViaje(fechaPlanDeViajeID,new FechaIda(new Date()),new FechaRegreso(new Date())),
                        new CupoMaximo(2)
                ),
                new ActividadCreado(ActividadID.of("1"),
                        new ActividadPlanDeVIaje("VISITAR UNA CRECIENTE")),
                new ActividadCreado(ActividadID.of("2"),
                        new ActividadPlanDeVIaje("VISITAR LA CATEDRAL")),
                new ActividadCreado(ActividadID.of("3"),
                        new ActividadPlanDeVIaje("VISITAR TEMPLO HISTORICO")),
                new ActividadCreado(ActividadID.of("4"),
                        new ActividadPlanDeVIaje("VISITAR LA IGLESIA MÁS GRANDE")),
                new ActividadCreado(ActividadID.of("5"),
                        new ActividadPlanDeVIaje("IR A PISCINA")),
                new ActividadCreado(ActividadID.of("6"),
                        new ActividadPlanDeVIaje("NOCHE DE BAILE")),
                new ActividadCreado(ActividadID.of("7"),
                        new ActividadPlanDeVIaje("VISITAR UN ZOOLOGICO")),
                new ActividadCreado(ActividadID.of("8"),
                        new ActividadPlanDeVIaje("IR AL TEATRO"))
        );
    }
}