package com.example.proyectofinciclo.res;

import com.example.proyectofinciclo.models.user;

public class ResUser {
    private int estado;
    private user user;
    private String mensaje;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

}