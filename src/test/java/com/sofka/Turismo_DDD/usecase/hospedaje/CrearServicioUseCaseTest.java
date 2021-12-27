package com.sofka.Turismo_DDD.usecase.hospedaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.commands.CrearServicioCommand;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.events.HospedajeCreado;
import com.sofka.Turismo_DDD.domain.hospedaje.events.ServicioCreado;
import com.sofka.Turismo_DDD.domain.hospedaje.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearServicioUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void CrearServicio() {
        HospedajeID hospedajeID = HospedajeID.of("1234");
        ServicioID servicioID = ServicioID.of("xxxx");
        TipoDeServicio tipoDeServicio = new TipoDeServicio("SERVICIO DE HABITACION");

        var command = new CrearServicioCommand(hospedajeID, servicioID, tipoDeServicio);
        var usecase = new CrearServicioUseCase();

        when(repository.getEventsBy("1234")).thenReturn(events());
        usecase.addRepository(repository);


        var evens = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(hospedajeID.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (ServicioCreado) evens.get(0);
        Assertions.assertEquals(servicioID, event.getServicioID());
        Assertions.assertEquals(tipoDeServicio, event.getTipoDeServicio());
        Mockito.verify(repository).getEventsBy("1234");
    }

    private List<DomainEvent> events() {
        Localizacion localizacion = new Localizacion(LocalizacionID.of("123"), new Ciudad("BOGOTA"));
        PrecioTotalHospedaje precioTotalHospedaje = new PrecioTotalHospedaje(280.0);
        return List.of(new HospedajeCreado(localizacion, precioTotalHospedaje));
    }

    @Test
    void CrearServicio_FallaPorCantidadDeServicioCreados() {
        HospedajeID hospedajeID = HospedajeID.of("1234");
        ServicioID servicioID = ServicioID.of("xxxx");
        TipoDeServicio tipoDeServicio = new TipoDeServicio("SERVICIO DE HABITACION");

        var command = new CrearServicioCommand(hospedajeID, servicioID, tipoDeServicio);
        var usecase = new CrearServicioUseCase();

        when(repository.getEventsBy("1234")).thenReturn(fullICondicionesEvents());
        usecase.addRepository(repository);

        var message = Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler
                    .getInstance()
                    .setIdentifyExecutor(hospedajeID.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();
        Assertions.assertEquals("Alcanzo el limite de servicios creados", message);
    }

    private List<DomainEvent> fullICondicionesEvents() {
        Localizacion localizacion = new Localizacion(LocalizacionID.of("123"), new Ciudad("BOGOTA"));
        PrecioTotalHospedaje precioTotalHospedaje = new PrecioTotalHospedaje(0.0);
        return List.of(new HospedajeCreado(localizacion, precioTotalHospedaje),
                new ServicioCreado(ServicioID.of("1"), new TipoDeServicio("LIMPIEZA")),
                new ServicioCreado(ServicioID.of("2"), new TipoDeServicio("WIFI")),
                new ServicioCreado(ServicioID.of("3"), new TipoDeServicio("PISCINA")),
                new ServicioCreado(ServicioID.of("4"), new TipoDeServicio("GIMNASIO")),
                new ServicioCreado(ServicioID.of("5"), new TipoDeServicio("RESTAURANTE"))
        );
    }
}