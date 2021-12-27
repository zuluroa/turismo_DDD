package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearPlanDeViajeCommand;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.FechaPlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.PlanDeViajeCreado;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaIda;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaPlanDeViajeID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.FechaRegreso;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;


class CrearPlanDeViajeUseCaseTest {

    @Test
    void CrearPlanDeViaje() {
        PlanDeViajeID planDeViajeID = PlanDeViajeID.of("1234");
        var fechaIda = Calendar.getInstance();
        fechaIda.set(2022, Calendar.JANUARY, 3);

        var fechaRegreso = Calendar.getInstance();
        fechaRegreso.set(2022, Calendar.JANUARY, 4);
        FechaPlanDeViaje fechaPlanDeViaje = new FechaPlanDeViaje(FechaPlanDeViajeID.of("fecha-01"), new FechaIda(fechaIda.getTime()), new FechaRegreso(fechaRegreso.getTime()));
        CupoMaximo cupoMaximo = new CupoMaximo(4);

        var command = new CrearPlanDeViajeCommand(planDeViajeID, fechaPlanDeViaje, cupoMaximo);
        var usecase = new CrearPlanDeViajeUseCase();

        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        PlanDeViajeCreado event = (PlanDeViajeCreado) events.getDomainEvents().get(0);
        Assertions.assertEquals("1234", event.aggregateRootId());
        Assertions.assertEquals(fechaPlanDeViaje, event.getFechaPlanDeViaje());
    }

    @Test
    void CrearPlanDeViaje_FalloPorFecha() {
        PlanDeViajeID planDeViajeID = PlanDeViajeID.of("1234");

        var fechaIda = Calendar.getInstance();
        fechaIda.set(2022, Calendar.JANUARY, 8);
        var fechaRegreso = Calendar.getInstance();
        fechaRegreso.set(2022, Calendar.JANUARY, 9);

        FechaPlanDeViaje fechaPlanDeViaje = new FechaPlanDeViaje(FechaPlanDeViajeID.of("fecha-01"), new FechaIda(fechaIda.getTime()), new FechaRegreso(fechaRegreso.getTime()));
        CupoMaximo cupoMaximo = new CupoMaximo(4);

        var command = new CrearPlanDeViajeCommand(planDeViajeID, fechaPlanDeViaje, cupoMaximo);
        var usecase = new CrearPlanDeViajeUseCase();

        var message = Assertions.assertThrows(BusinessException.class, () -> {
            var events = UseCaseHandler.getInstance()
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();


        Assertions.assertEquals("No puede crear fechas en fines de semana", message);
    }
}