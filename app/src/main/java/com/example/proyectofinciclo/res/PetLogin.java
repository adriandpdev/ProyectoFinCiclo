package com.example.proyectofinciclo.res;

import com.google.gson.annotations.SerializedName;

public class PetLogin {
    @SerializedName("User")
    private String User;

    @SerializedName("Pass")
    private String Pass;


    public PetLogin(String user, String pass){
        this.User=user;
        this.Pass=pass;
    }

    public String getuser() {
        return User;
    }

    public void setUser(String user) {
        this.User = user;
    }

    public String getPass() {
        return Pass;
    }

    public void pass(String pass) {
        this.Pass = pass;
    }
}