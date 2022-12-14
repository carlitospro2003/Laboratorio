package com.ramirez.laboratorio.api;

public class Temperatura {
    private int last_value;

    public Temperatura(int last_value){this.last_value= last_value;}

    public int getNumero() {
        return last_value;
    }

    public void setNumero(int numeros) {
        this.last_value = numeros;
    }
}
