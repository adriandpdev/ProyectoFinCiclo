package com.example.proyectofinciclo.res;

import com.example.proyectofinciclo.models.equipo;

import java.util.List;

public class ResCalendario {
    private int estado;
    private List<equipo> equipos;
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

    public List<equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<equipo> equipos) {
        this.equipos = equipos;
    }

}