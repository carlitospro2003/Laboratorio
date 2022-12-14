package com.ramirez.laboratorio.api;

public class Magnetico {
    private int last_value;

    public Magnetico(int last_value){this.last_value= last_value;}

    public int getMagneticoNumero() {
        return last_value;
    }

    public void setMagneticoNumero(int numeros) {
        this.last_value = numeros;
    }
}
