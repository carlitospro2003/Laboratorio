package com.ramirez.laboratorio.api;

public class Humo {
        private int last_value;

        public Humo(int last_value){this.last_value= last_value;}

        public int getHumoNumero() {
            return last_value;
        }

        public void setHumoNumero(int numeros) {
            this.last_value = numeros;
        }
    }