package com.example.proyectofinciclo;

import com.example.proyectofinciclo.res.PetLogin;
import com.example.proyectofinciclo.res.ResCalendario;
import com.example.proyectofinciclo.res.ResNews;
import com.example.proyectofinciclo.res.ResPlantilla;
import com.example.proyectofinciclo.res.ResUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    @GET("plantilla")
    Call<ResPlantilla> getPlantilla();

    @GET("calendario")
    Call<ResCalendario> getCalendario();

    @GET("calendariohome")
    Call<ResCalendario> getCalendarioHome();

    @GET("noticias")
    Call<ResNews> getNews();

    @POST("login")
    Call<ResUser> getLogin(PetLogin pLogin);

    @POST("register")
    Call<ResUser> getRegister();
}