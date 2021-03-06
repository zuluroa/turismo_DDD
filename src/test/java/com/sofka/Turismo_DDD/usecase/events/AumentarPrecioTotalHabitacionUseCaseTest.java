package com.sofka.Turismo_DDD.usecase.events;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.Hospedaje;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.events.PrecioTotalHospedajeActulizado;
import com.sofka.Turismo_DDD.domain.hospedaje.values.Ciudad;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HospedajeID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.LocalizacionID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioTotalHospedaje;
import com.sofka.Turismo_DDD.usecase.hospedaje.AumentarPrecioTotalHabitacionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AumentarPrecioTotalHabitacionUseCaseTest {

    Hospedaje hospedaje = new Hospedaje(HospedajeID.of("123"), new Localizacion(LocalizacionID.of("123"), new Ciudad("CUCUTA")), new PrecioTotalHospedaje(30.0));
    @Mock
    private DomainEventRepository repository;

    @Test
    void aumentarPrecioTotalHabitacion() {
        var event = new PrecioTotalHospedajeActulizado(hospedaje, new PrecioTotalHospedaje(50.0));
        event.setAggregateRootId("xxxx");
        var useCase = new AumentarPrecioTotalHabitacionUseCase();

        Mockito.when(repository.getEventsBy(event.aggregateRootId())).thenReturn(events());
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(event.aggregateRootId())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var precioHabitacion = (PrecioTotalHospedajeActulizado) events.get(0);
        Assertions.assertEquals(
                110.0, precioHabitacion.getPrecioTotalHabitacion().value());
    }

    private List<DomainEvent> events() {
        return List.of(
                new PrecioTotalHospedajeActulizado(hospedaje, new PrecioTotalHospedaje(60.0))
        );
    }
}