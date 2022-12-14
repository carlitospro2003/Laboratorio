package com.ramirez.laboratorio;

public class Respuestas {
    String mensaje;
    String token;
    String id;

    public Respuestas(String mensaje, String Token,String Id){this.mensaje=mensaje;
    this.token=Token;}

    public Object getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
