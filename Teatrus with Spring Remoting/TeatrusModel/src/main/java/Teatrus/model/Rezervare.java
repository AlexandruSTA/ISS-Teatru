package Teatrus.model;

import java.io.Serializable;

public class Rezervare implements Serializable {
    private int idRezervare,idClient,idSpectacol,numarLocuri;

    public Rezervare(){
        this.idRezervare=this.idClient=this.numarLocuri=this.idSpectacol=0;
    }

    public Rezervare(int idRezervare, int idClient,int idSpectacol,int numarLocuri) {
        this.idRezervare = idRezervare;
        this.idClient = idClient;
        this.numarLocuri = numarLocuri;
        this.idSpectacol = idSpectacol;
    }

    public int getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(int idRezervare) {
        this.idRezervare = idRezervare;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }



    public int getIdSpectacol() {
        return idSpectacol;
    }

    public void setIdSpectacol(int idSpectacol) {
        this.idSpectacol = idSpectacol;
    }

    public int getNumarLocuri() {
        return numarLocuri;
    }

    public void setNumarLocuri(int numarLocuri) {
        this.numarLocuri = numarLocuri;
    }
}
