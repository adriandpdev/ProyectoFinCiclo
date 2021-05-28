package com.example.proyectofinciclo.res;

import com.google.gson.annotations.SerializedName;

public class PetRegister {
    @SerializedName("User")
    private String User;

    @SerializedName("Pass")
    private String Pass;

    @SerializedName("Email")
    private String Email;

    @SerializedName("Socio")
    private String Socio;


    public PetRegister(String user, String pass,String email, String socio){
        this.User=user;
        this.Pass=pass;
        this.Email=email;
        this.Socio=socio;
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

    public void setpass(String pass) {
        this.Pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSocio() {
        return Socio;
    }

    public void setSocio(String Socio) {
        this.Socio = Socio;
    }
}