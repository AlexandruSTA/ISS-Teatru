package Teatrus.model;

import java.io.Serializable;

public class ModalitateDePlata implements Serializable {
    private TipDePlata tipDePlata;
    private int suma;
    private StatusPlata status;

    public ModalitateDePlata() {
        this.tipDePlata=null;
        this.suma=0;
        this.status=null;
    }

    public ModalitateDePlata(TipDePlata tipDePlata, int suma, StatusPlata status) {
        this.tipDePlata = tipDePlata;
        this.suma = suma;
        this.status = status;
    }

    public TipDePlata getTipDePlata() {
        return tipDePlata;
    }

    public void setTipDePlata(TipDePlata tipDePlata) {
        this.tipDePlata = tipDePlata;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public StatusPlata getStatus() {
        return status;
    }

    public void setStatus(StatusPlata status) {
        this.status = status;
    }
}
