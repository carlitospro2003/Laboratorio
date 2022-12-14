package com.ramirez.laboratorio.api;

public class Potencia {
    private int last_value;

    public Potencia(int last_value){this.last_value= last_value;}

    public int getPotenciaNumero() {
        return last_value;
    }

    public void setPotenciaNumero(int numeros) {
        this.last_value = numeros;
    }
}

