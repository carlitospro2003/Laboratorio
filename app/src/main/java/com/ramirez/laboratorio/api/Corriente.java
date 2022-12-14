package com.ramirez.laboratorio.api;

public class Corriente {
    private int last_value;

    public Corriente(int last_value){this.last_value= last_value;}

    public int getCorrienteNumero() {
        return last_value;
    }

    public void setCorrienteNumero(int numeros) {
        this.last_value = numeros;
    }
}
