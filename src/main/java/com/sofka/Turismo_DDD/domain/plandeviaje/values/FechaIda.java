package com.sofka.Turismo_DDD.domain.plandeviaje.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class FechaIda implements ValueObject<Date> {
    private final Date value;

    public FechaIda(Date value) {
        this.value = Objects.requireNonNull(value, "La fecha no puede ser null");
        if(value.before(new Date(Instant.now().toEpochMilli()))){
            throw new IllegalArgumentException("No puede colocar una fecha del pasado");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Date value() {
        return value;
    }
}
