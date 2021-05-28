package com.example.proyectofinciclo;

import com.example.proyectofinciclo.res.PetLogin;
import com.example.proyectofinciclo.res.PetRegister;
import com.example.proyectofinciclo.res.ResCalendario;
import com.example.proyectofinciclo.res.ResClasi;
import com.example.proyectofinciclo.res.ResNews;
import com.example.proyectofinciclo.res.ResPlantilla;
import com.example.proyectofinciclo.res.ResUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {


    @GET("plantilla")
    Call<ResPlantilla> getPlantilla();

    @GET("calendario")
    Call<ResCalendario> getCalendario();

    @GET("calendariohome")
    Call<ResCalendario> getCalendarioHome();

    @GET("clasi/2bf2")
    Call<ResClasi> getClasi();

    @GET("noticias")
    Call<ResNews> getNews();

    @Headers({"Accept: application/json"})
    @POST("auth/login")
    Call<ResUser> getLogin(
            @Body PetLogin body
    );

    @Headers({"Accept: application/json"})
    @POST("auth/register")
    Call<ResUser> getRegister(
            @Body PetRegister body
    );
}