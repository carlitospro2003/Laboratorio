package com.ramirez.laboratorio.api;

public class Registrar {
    String id;
    String email;

    public Registrar(String id, String email){this.id=id;
        this.email=email;}

    public String getEmail(){return email;}

    public void SetEmail(String email){this.email=email;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
