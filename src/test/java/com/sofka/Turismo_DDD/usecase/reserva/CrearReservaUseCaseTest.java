package com.sofka.Turismo_DDD.usecase.reserva;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.sofka.Turismo_DDD.domain.genericvalues.Contacto;
import com.sofka.Turismo_DDD.domain.genericvalues.Direccion;
import com.sofka.Turismo_DDD.domain.genericvalues.Identificacion;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.commands.CrearReservaCommand;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.events.ReservaCreada;
import com.sofka.Turismo_DDD.domain.reserva.values.Fecha;
import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;
import com.sofka.Turismo_DDD.domain.reserva.values.VendedorID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;


class CrearReservaUseCaseTest {

    @Test
    void CrearReserva() {

        //Arrange
        ReservaID reservaID = ReservaID.of("1234");
        VendedorID vendedorID = VendedorID.of("1234");
        Vendedor vendedor = new Vendedor(vendedorID,
                new Identificacion("CEDULA", "1002201980"),
                new Nombre("David", "Zuluaga"),
                new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                new Contacto("3202040834", "zuluroa@gmail.com"));
        Fecha fecha = new Fecha(new Date(2022, 02, 02, 06, 30));
        var command = new CrearReservaCommand(reservaID, vendedor, fecha);
        var usecase = new CrearReservaUseCase();

        //Act
        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        //asserts
        ReservaCreada event = (ReservaCreada) events.getDomainEvents().get(0);
        Assertions.assertEquals("1234", event.aggregateRootId());
        Assertions.assertEquals(vendedor, event.getVendedor());
    }

    @Test
    void CrearReserva_FallaPorVendedorNoTieneNombre() {
        ReservaID reservaID = ReservaID.of("1234");
        VendedorID vendedorID = VendedorID.of("1234");
        Vendedor vendedor = new Vendedor(vendedorID,
                new Identificacion("CEDULA", "1002201980"),
                new Nombre("NO TIENE", "NO TIENE"),
                new Direccion("Los Patios", "av 12 Cll 9 #11-130"),
                new Contacto("3202040834", "zuluroa@gmail.com"));
        Fecha fecha = new Fecha(new Date(2022, 02, 02, 06, 30));
        var command = new CrearReservaCommand(reservaID, vendedor, fecha);
        var usecase = new CrearReservaUseCase();

        //act
        var message = Assertions.assertThrows(BusinessException.class, () -> {
            var events = UseCaseHandler.getInstance()
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();


        Assertions.assertEquals("El vendedor debe tener un nombre", message);
    }

}