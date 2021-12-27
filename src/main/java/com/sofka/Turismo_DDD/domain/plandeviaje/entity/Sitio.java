package com.sofka.Turismo_DDD.domain.plandeviaje.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.SitioID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.SitioPlanDeVIaje;

import java.util.Objects;

import static com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo.compararSiNuloOEsVacio;
import static com.sofka.Turismo_DDD.domain.utils.mayorOIgual.mayorOIgualQue;

public class Sitio extends Entity<SitioID> {
    private final SitioID sitioID;
    private  SitioPlanDeVIaje sitio;

    public Sitio(SitioID sitioID, SitioPlanDeVIaje sitio) {
        super(sitioID);
        this.sitioID = Objects.requireNonNull(sitioID, "El ID del sitio no puede estar vacio");
        this.sitio = Objects.requireNonNull(sitio, "El sitio no puede ser vacio");
        if(compararSiNuloOEsVacio(sitio.value()))throw new IllegalArgumentException("El sitio del plan de viaje no puede ser vacio");
        if(mayorOIgualQue(sitio.value().length() , 6)) throw new IllegalArgumentException("El sitio del plan de viaje debe ser mayor de 3 caracteres");
    }

    public void ActualizarSitio(SitioPlanDeVIaje sitioPlanDeVIaje){
        this.sitio = sitioPlanDeVIaje;
    }

    public SitioID getEntityId() {
        return sitioID;
    }

    public SitioPlanDeVIaje getSitio() {
        return sitio;
    }
}
