package Teatrus.model;

import java.io.Serializable;

public class Loc implements Serializable {
    private int idLoc,idSpectacol;
    private Pozitie pozitie;
    private int pret;
    private StatusLoc valabilitate;

    public Loc(int idLoc, int idSpectacol, Pozitie pozitie, int pret, StatusLoc valabilitate) {
        this.idLoc = idLoc;
        this.idSpectacol = idSpectacol;
        this.pozitie = pozitie;
        this.pret = pret;
        this.valabilitate = valabilitate;
    }

    public int getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(int idLoc) {
        this.idLoc = idLoc;
    }

    public int getIdSpectacol() {
        return idSpectacol;
    }

    public void setIdSpectacol(int idSpectacol) {
        this.idSpectacol = idSpectacol;
    }

    public Pozitie getPozitie() {
        return pozitie;
    }

    public void setPozitie(Pozitie pozitie) {
        this.pozitie = pozitie;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public StatusLoc getValabilitate() {
        return valabilitate;
    }

    public void setValabilitate(StatusLoc valabilitate) {
        this.valabilitate = valabilitate;
    }
}
