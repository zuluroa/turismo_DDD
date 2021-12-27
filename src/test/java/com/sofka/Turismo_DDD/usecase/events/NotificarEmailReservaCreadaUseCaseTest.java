package com.sofka.Turismo_DDD.usecase.events;

import co.com.sofka.business.generic.ServiceBuilder;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.Contacto;
import com.sofka.Turismo_DDD.domain.genericvalues.Direccion;
import com.sofka.Turismo_DDD.domain.genericvalues.Identificacion;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.events.ReservaCreada;
import com.sofka.Turismo_DDD.domain.reserva.values.Fecha;
import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;
import com.sofka.Turismo_DDD.domain.reserva.values.VendedorID;
import com.sofka.Turismo_DDD.usecase.reserva.NotificarEmailReservaCreadaUseCase;
import com.sofka.Turismo_DDD.usecase.reserva.SendEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificarEmailReservaCreadaUseCaseTest {

    @Mock
    SendEmailService service;

    @Test
    void notificarEmailUse() {
        Vendedor vendedor = new Vendedor(VendedorID.of("xxxx"),
                new Identificacion("CEDULA", "1002201980"),
                new Nombre("David", "Zuluaga"),
                new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                new Contacto("3202040834", "zuluroa@gmail.com"));
        Fecha fecha = new Fecha(new Date(2022, 02, 02, 06, 30));
        var event = new ReservaCreada(vendedor, fecha);
        event.setAggregateRootId("1234");
        var usecase = new NotificarEmailReservaCreadaUseCase();

        when(service.send(ReservaID.of(event.aggregateRootId()), "support@sofka.com.co", "Nueva reserva con fecha " + event.getFecha().value())).thenReturn(true);

        ServiceBuilder builder = new ServiceBuilder();
        builder.addService(service);
        usecase.addServiceBuilder(builder);

        var events = UseCaseHandler.getInstance()
                .syncExecutor(usecase, new TriggeredEvent<>(event))
                .orElseThrow();

        verify(service).send(ReservaID.of(event.aggregateRootId()),
                "support@sofka.com.co",
                "Nueva reserva con fecha " + event.getFecha().value());
    }

}