package com.ramirez.laboratorio;

import java.util.List;

public class PreSalones {
    private List<Salones> datos;

    public PreSalones(List<Salones> datos)
    {
        this.datos = datos;
    }

    public List<Salones> getSalones() {
        return datos;
    }

    public void setDatos(List<Salones> datos) {
        this.datos = datos;
    }
}
