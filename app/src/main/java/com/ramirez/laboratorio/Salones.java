package com.ramirez.laboratorio;

import android.view.View;

public class Salones {
    private Integer id;
    private String nombre;
    private String edificio;

    public Salones (Integer Id,String Nombre, String Edificio){
        this.id=Id;
        this.nombre = Nombre;
        this.edificio = Edificio;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String Edificio) {
        edificio = Edificio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
