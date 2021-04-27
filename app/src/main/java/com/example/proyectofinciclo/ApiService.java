package com.example.proyectofinciclo;

import com.example.proyectofinciclo.res.ResCalendario;
import com.example.proyectofinciclo.res.ResPlantilla;
import com.example.proyectofinciclo.res.ResNews;
import com.example.proyectofinciclo.res.ResUser;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("plantilla")
    Call<ResPlantilla> getPlantilla();

    @GET("calendario")
    Call<ResCalendario> getCalendario();

    @GET("calendariohome")
    Call<resCalendario> getCalendarioHome();

    @GET("noticias")
    Call<resNews> getNews();

    @POST("login")
    Call<resUser> getLogin();

    @POST("register")
    Call<resUser> getRegister();
}