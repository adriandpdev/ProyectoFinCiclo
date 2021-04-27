package com.example.proyectofinciclo.res;

import com.example.proyectofinciclo.models.news;

import java.util.List;

public class ResNews {
    private int estado;
    private List<news> news;
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

    public List<news> getNews() {
        return news;
    }

    public void setNews(List<news> news) {
        this.news = news;
    }

}