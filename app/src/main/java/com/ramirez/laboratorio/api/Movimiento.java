package com.ramirez.laboratorio.api;

public class Movimiento {
    private int last_value;

    public Movimiento(int last_value){this.last_value= last_value;}

    public int getMovimientoNumero() {
        return last_value;
    }

    public void setMovimientoNumero(int numeros) {
        this.last_value = numeros;
    }
}
