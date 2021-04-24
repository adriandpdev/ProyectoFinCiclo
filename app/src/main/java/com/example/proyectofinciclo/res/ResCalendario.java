package com.example.proyectofinciclo.res;

import com.example.proyectofinciclo.models.jugador;

import java.util.List;

public class ResPlantilla {
    private int estado;
    private List<jugador> jugadores;
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

    public List<jugador> getjugadores() {
        return jugadores;
    }

    public void setjugadores(List<jugador> jugadores) {
        this.jugadores = jugadores;
    }


}