package com.eattingapp;

public class Zamowienia {
    private String nazwa;
    private String user;

    public Zamowienia(String n, String u) {
        this.nazwa=n;
        this.user=u;
    }
    public Zamowienia() {
        this.nazwa="";
        this.user="";
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
