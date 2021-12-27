package com.sofka.Turismo_DDD.usecase.hospedaje;


import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.hospedaje.commands.CrearHabitacionCommand;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.events.HabitacionCreada;
import com.sofka.Turismo_DDD.domain.hospedaje.events.HospedajeCreado;
import com.sofka.Turismo_DDD.domain.hospedaje.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearHabitacionUseCaseTest {

    @Mock
    DomainEventRepository repository;

    Localizacion localizacion = new Localizacion(LocalizacionID.of("123"),new Ciudad("BOGOTA"));
    PrecioTotalHospedaje precioTotalHospedaje =  new PrecioTotalHospedaje(280.0);

    @Test
     void CrearHabitacion(){
        HospedajeID hospedajeID =  HospedajeID.of("1234");
        HabitacionID habitacionID = HabitacionID.of("xxxx");
        CupoMaximo cupoMaximo = new CupoMaximo(4);
        TipoDeHabitacion tipoDeHabitacion = new TipoDeHabitacion("HABITACION ESTANDAR");
        PrecioHabitacion precioHabitacion = new PrecioHabitacion(20.0);

        var command = new CrearHabitacionCommand(hospedajeID,habitacionID,cupoMaximo,tipoDeHabitacion,precioHabitacion);
        var usecase = new CrearHabitacionUseCase();

        when(repository.getEventsBy("1234")).thenReturn(events());
        usecase.addRepository(repository);


        var evens = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(hospedajeID.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();


        var event = (HabitacionCreada) evens.get(1);
        Assertions.assertEquals("xxxx",event.getHabitacionID().value());
        Assertions.assertEquals(4,event.getCupoMaximo().value());
        Assertions.assertEquals("HABITACION ESTANDAR",event.getTipoDeHabitacion().value());
        Assertions.assertEquals(20.0,event.getPrecioHabitacion().value());

    }

    private List<DomainEvent> events() {
        HabitacionID habitacionID = HabitacionID.of("xxxx");
        CupoMaximo cupoMaximo = new CupoMaximo(4);
        TipoDeHabitacion tipoDeHabitacion = new TipoDeHabitacion("HABITACION ESTANDAR");
        PrecioHabitacion precioHabitacion = new PrecioHabitacion(20.0);
        return List.of(new HospedajeCreado(localizacion,precioTotalHospedaje),
                new HabitacionCreada(habitacionID,cupoMaximo,tipoDeHabitacion,precioHabitacion));
    }

    @Test
     void CrearServicio_FallaPorCantidadDeServicioCreados(){
        HospedajeID hospedajeID =  HospedajeID.of("1234");
        HabitacionID habitacionID = HabitacionID.of("xxxx");
        CupoMaximo cupoMaximo = new CupoMaximo(4);
        TipoDeHabitacion tipoDeHabitacion = new TipoDeHabitacion("HABITACION ESTANDAR");
        PrecioHabitacion precioHabitacion = new PrecioHabitacion(20.0);

        var command = new CrearHabitacionCommand(hospedajeID,habitacionID,cupoMaximo,tipoDeHabitacion,precioHabitacion);
        var usecase = new CrearHabitacionUseCase();

        when(repository.getEventsBy("1234")).thenReturn(fullIHabitacionesEvents());
        usecase.addRepository(repository);

        var message = Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler
                    .getInstance()
                    .setIdentifyExecutor(hospedajeID.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();
        Assertions.assertEquals("Alcanzo el limite de habitaciones creadas",message);
    }

    private List<DomainEvent> fullIHabitacionesEvents() {
        CupoMaximo cupoMaximo = new CupoMaximo(4);
        TipoDeHabitacion tipoDeHabitacion = new TipoDeHabitacion("HABITACION ESTANDAR");
        PrecioHabitacion precioHabitacion = new PrecioHabitacion(20.0);
        return List.of(new HospedajeCreado(localizacion,precioTotalHospedaje),
                new HabitacionCreada(HabitacionID.of("1"),cupoMaximo,tipoDeHabitacion,precioHabitacion),
                new HabitacionCreada(HabitacionID.of("2"),cupoMaximo,tipoDeHabitacion,precioHabitacion),
                new HabitacionCreada(HabitacionID.of("3"),cupoMaximo,tipoDeHabitacion,precioHabitacion),
                new HabitacionCreada(HabitacionID.of("4"),cupoMaximo,tipoDeHabitacion,precioHabitacion)
        );
    }
}