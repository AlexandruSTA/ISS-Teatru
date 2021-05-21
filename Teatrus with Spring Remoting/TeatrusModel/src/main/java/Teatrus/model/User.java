package Teatrus.model;

import java.io.Serializable;

public class User implements Serializable {
    private int idUser,tipUtilizator;
    protected String nume,prenume,email,numeUtilizator,parola;

    public User() {
        this.idUser=this.tipUtilizator=0;
        this.nume=this.prenume=this.email=this.numeUtilizator= this.parola="";
    }

    public User(int idUser, String nume, String prenume, String numeUtilizator, String parola, int tipUtilizator) {
        this.idUser = idUser;
        this.nume = nume;
        this.prenume = prenume;
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
        this.tipUtilizator = tipUtilizator;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getTipUtilizator() {
        return tipUtilizator;
    }

    public void setTipUtilizator(int tipUtilizator) {
        this.tipUtilizator = tipUtilizator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void copy(User u){
        this.idUser=u.idUser;
        this.nume=u.nume;
        this.prenume=u.prenume;
        this.email=u.email;
        this.numeUtilizator=u.numeUtilizator;
        this.parola=u.parola;
        this.tipUtilizator=u.tipUtilizator;
    }
}
