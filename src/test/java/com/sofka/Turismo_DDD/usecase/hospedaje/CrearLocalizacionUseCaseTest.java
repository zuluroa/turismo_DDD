package com.sofka.Turismo_DDD.usecase.hospedaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.commands.CrearLocalizacionCommand;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.events.HospedajeCreado;
import com.sofka.Turismo_DDD.domain.hospedaje.events.LocalizacionCreada;
import com.sofka.Turismo_DDD.domain.hospedaje.values.Ciudad;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HospedajeID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.LocalizacionID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioTotalHospedaje;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearLocalizacionUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void CrearLocalizacion() {
        HospedajeID hospedajeID = HospedajeID.of("1234");
        Localizacion localizacion = new Localizacion(LocalizacionID.of("123"), new Ciudad("CUCUTA"));
        var command = new CrearLocalizacionCommand(hospedajeID, localizacion);
        var usecase = new CrearLocalizacionUseCase();

        when(repository.getEventsBy("1234")).thenReturn(events());
        usecase.addRepository(repository);

        var evens = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(hospedajeID.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (LocalizacionCreada) evens.get(0);
        Assertions.assertEquals(localizacion, event.getLocalizacionID());
    }

    private List<DomainEvent> events() {
        Localizacion localizacion = new Localizacion(LocalizacionID.of("123"), new Ciudad("BOGOTA"));
        PrecioTotalHospedaje precioTotalHospedaje = new PrecioTotalHospedaje(280.0);
        return List.of(new HospedajeCreado(localizacion, precioTotalHospedaje));
    }

    @Test
    void CrearLocalizacion_FalloPorCiudad() {
        HospedajeID hospedajeID = HospedajeID.of("1234");
        Localizacion localizacion = new Localizacion(LocalizacionID.of("123"), new Ciudad("DESCONOCIDO"));
        var command = new CrearLocalizacionCommand(hospedajeID, localizacion);
        var usecase = new CrearLocalizacionUseCase();

        when(repository.getEventsBy("1234")).thenReturn(Failevents());
        usecase.addRepository(repository);

        var message = Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler
                    .getInstance()
                    .setIdentifyExecutor(hospedajeID.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();
        Assertions.assertEquals("La ciudad no puede ser desconocida", message);
    }

    private List<DomainEvent> Failevents() {
        Localizacion localizacionPruebaFallo = new Localizacion(LocalizacionID.of("123"), new Ciudad("DESCONOCIDO"));
        Localizacion localizacion = new Localizacion(LocalizacionID.of("123"), new Ciudad("BOGOTA"));
        PrecioTotalHospedaje precioTotalHospedaje = new PrecioTotalHospedaje(280.0);
        return List.of(new HospedajeCreado(localizacion, precioTotalHospedaje),
                new LocalizacionCreada(localizacionPruebaFallo));
    }
}