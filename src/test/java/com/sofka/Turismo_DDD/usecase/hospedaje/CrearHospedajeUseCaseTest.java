package com.sofka.Turismo_DDD.usecase.hospedaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.sofka.Turismo_DDD.domain.hospedaje.commands.CrearHospedajeCommand;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.events.HospedajeCreado;
import com.sofka.Turismo_DDD.domain.hospedaje.values.Ciudad;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HospedajeID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.LocalizacionID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioTotalHospedaje;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CrearHospedajeUseCaseTest {

    @Test
    void CrearHospedaje() {

        var command = new CrearHospedajeCommand(HospedajeID.of("1234"),
                new Localizacion(LocalizacionID.of("1223"), new Ciudad("CUCUTA")),
                new PrecioTotalHospedaje(20.0));

        var usecase = new CrearHospedajeUseCase();

        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        HospedajeCreado event = (HospedajeCreado) events.getDomainEvents().get(0);
        Assertions.assertEquals("1234", event.aggregateRootId());
        Assertions.assertEquals(command.getLocalizacion(), event.getLocalizacion());
    }

    @Test
    void CrearHospedaje_falloCiudadDesconocida() {
        var command = new CrearHospedajeCommand(HospedajeID.of("1234"),
                new Localizacion(LocalizacionID.of("1223"), new Ciudad("DESCONOCIDO")),
                new PrecioTotalHospedaje(20.0));

        var usecase = new CrearHospedajeUseCase();

        var message = Assertions.assertThrows(BusinessException.class, () -> {
            var events = UseCaseHandler.getInstance()
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();


        Assertions.assertEquals("La ciudad no puede ser desconocida", message);
    }
}