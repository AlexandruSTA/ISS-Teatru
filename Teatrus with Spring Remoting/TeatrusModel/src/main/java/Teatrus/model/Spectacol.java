package Teatrus.model;

import java.io.Serializable;
import java.util.Date;

public class Spectacol implements Serializable {
    private int idSpectacol;
    private String titlu,descriere;
    private Date dataInceput;
    private Date dataSfarsit;
    private String adresaAfis;

    public Spectacol() {
        this.idSpectacol=0;
        this.titlu=this.descriere=this.adresaAfis="";
        this.dataInceput=this.dataSfarsit=new Date();
    }

    public Spectacol(int idSpectacol, String titlu, String descriere, Date dataInceput) {
        this.idSpectacol = idSpectacol;
        this.titlu = titlu;
        this.descriere = descriere;
        this.dataInceput = dataInceput;
    }

    public int getIdSpectacol() {
        return idSpectacol;
    }

    public void setIdSpectacol(int idSpectacol) {
        this.idSpectacol = idSpectacol;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Date getDataSfarsit() {
        return dataSfarsit;
    }

    public void setDataSfarsit(Date dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public String getAdresaAfis(){
        return this.adresaAfis;
    }

    public void setAdresaAfis(String adresaAfis){
        this.adresaAfis=adresaAfis;
    }
}
