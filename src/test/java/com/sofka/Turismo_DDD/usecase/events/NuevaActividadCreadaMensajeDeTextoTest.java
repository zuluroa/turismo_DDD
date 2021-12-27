package com.sofka.Turismo_DDD.usecase.events;

import co.com.sofka.business.generic.ServiceBuilder;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.ActividadCreado;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.ActividadPlanDeVIaje;
import com.sofka.Turismo_DDD.usecase.plandeviaje.NuevaActividadCreadaMensajeDeTexto;
import com.sofka.Turismo_DDD.usecase.plandeviaje.SendMensajeTexto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NuevaActividadCreadaMensajeDeTextoTest {

    @Mock
    SendMensajeTexto service;

    @Test
    void nuevaActividadCreada() {
        var event = new ActividadCreado(ActividadID.of("1234"), new ActividadPlanDeVIaje("ACAMPPAR"));
        event.setAggregateRootId("xxxx");
        var usecase = new NuevaActividadCreadaMensajeDeTexto();

        when(service.sendMessage("3202040834",
                "NUEVA ACTIVIDAD CREADA! " + event.getActividadPlanDeVIaje().value())).thenReturn(true);

        ServiceBuilder builder = new ServiceBuilder();
        builder.addService(service);
        usecase.addServiceBuilder(builder);

        var events = UseCaseHandler.getInstance()
                .syncExecutor(usecase, new TriggeredEvent<>(event))
                .orElseThrow();

        verify(service).sendMessage("3202040834",
                "NUEVA ACTIVIDAD CREADA! " + event.getActividadPlanDeVIaje().value());
    }
}