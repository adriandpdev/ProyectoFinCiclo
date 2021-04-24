package com.example.proyectofinciclo;

import com.example.proyectofinciclo.res.ResCalendario;
import com.example.proyectofinciclo.res.ResPlantilla;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("plantilla")
    Call<ResPlantilla> getPlantilla();

    @GET("calendario")
    Call<ResCalendario> getCalendario();
}