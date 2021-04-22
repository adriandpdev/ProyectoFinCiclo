package com.example.proyectofinciclo.models;

public class resul {
    public resul(String date, String homegol, String awaygol, String homename, String awayname, String penals){
        setDate(date);
        setHomegol(homegol);
        setAwaygol(awaygol);
        setHomename(homename);
        setAwayname(awayname);
        setPenals(penals);
    }
    private String date,homegol,awaygol,homename,awayname, penals;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHomegol() {
        return homegol;
    }
    public void setHomegol(String homegol) {
        this.homegol = homegol;
    }
    public String getAwaygol() {
        return awaygol;
    }
    public void setAwaygol(String awaygol) {
        this.awaygol = awaygol;
    }
    public String getHomename() {
        return homename;
    }
    public void setHomename(String homename) {
        this.homename = homename;
    }
    public String getAwayname() {
        return awayname;
    }
    public void setAwayname(String awayname) {
        this.awayname = awayname;
    }
    public String getPenals() {
        return penals;
    }
    public void setPenals(String penals) {
        this.penals = penals;
    }

}