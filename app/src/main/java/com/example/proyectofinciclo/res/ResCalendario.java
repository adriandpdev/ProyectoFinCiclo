package com.example.proyectofinciclo.res;

import com.example.proyectofinciclo.models.partido;

import java.util.List;

public class ResCalendario {
    private int estado;
    private List<partido> partidos;
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

    public List<partido> getPartidos() { return partidos; }

    public void setPartidos(List<partido> partidos) {
        this.partidos = partidos;
    }


}