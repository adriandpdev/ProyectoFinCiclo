package com.example.proyectofinciclo.res;

import com.example.proyectofinciclo.models.equipo;

import java.util.List;

public class ResClasi {
    private int estado;
    private List<equipo> clasi;
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
        return clasi;
    }

    public void setEquipos(List<equipo> clasi) {
        this.clasi = clasi;
    }

}