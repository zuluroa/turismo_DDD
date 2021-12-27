package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearSitioCommand;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
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
class CrearSitioUseCaseTest {
    @Mock
    DomainEventRepository repository;

    @Test
    void CrearSitio() {
        PlanDeViajeID planDeViajeID = PlanDeViajeID.of("1234");
        SitioID sitioID = SitioID.of("xxxx");
        SitioPlanDeVIaje sitio = new SitioPlanDeVIaje("COLOMBIA");
        var command = new CrearSitioCommand(planDeViajeID, sitioID, sitio);
        var usecase = new CrearSitioUseCase();

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
        var event = (SitioCreado) evens.get(0);
        Assertions.assertEquals(sitioID, event.getSitioID());
        Assertions.assertEquals(sitio, event.getSitio());
        Mockito.verify(repository).getEventsBy("1234");
    }

    private List<DomainEvent> events() {
        FechaPlanDeViajeID fechaPlanDeViajeID = FechaPlanDeViajeID.of("yyyyy");
        return List.of(new PlanDeViajeCreado(new FechaPlanDeViaje(fechaPlanDeViajeID, new FechaIda(new Date()), new FechaRegreso(new Date())),
                new CupoMaximo(2)
        ));
    }

    @Test
    void CrearSitio_FallaPorTopeSitiosAgregados() {
        PlanDeViajeID planDeViajeID = PlanDeViajeID.of("1234");
        SitioID sitioID = SitioID.of("xxxx");
        SitioPlanDeVIaje sitio = new SitioPlanDeVIaje("COLOMBIA");
        var command = new CrearSitioCommand(planDeViajeID, sitioID, sitio);
        var usecase = new CrearSitioUseCase();

        when(repository.getEventsBy("1234")).thenReturn(fullICondicionesEvents());
        usecase.addRepository(repository);

        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler
                    .getInstance()
                    .setIdentifyExecutor(planDeViajeID.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        });
    }

    private List<DomainEvent> fullICondicionesEvents() {
        FechaPlanDeViajeID fechaPlanDeViajeID = FechaPlanDeViajeID.of("yyyyy");
        return List.of(new PlanDeViajeCreado(new FechaPlanDeViaje(fechaPlanDeViajeID, new FechaIda(new Date()), new FechaRegreso(new Date())),
                        new CupoMaximo(2)
                ),
                new SitioCreado(SitioID.of("1"),
                        new SitioPlanDeVIaje("BOGOTA")),
                new SitioCreado(SitioID.of("2"),
                        new SitioPlanDeVIaje("MEDELLIN")),
                new SitioCreado(SitioID.of("3"),
                        new SitioPlanDeVIaje("VALLE DEL CAUTA"))
        );
    }
}