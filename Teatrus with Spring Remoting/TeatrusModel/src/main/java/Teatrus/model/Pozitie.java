package Teatrus.model;

import java.io.Serializable;

public class Pozitie implements Serializable {
    private int rand,coloana;

    public Pozitie(int rand, int coloana) {
        this.rand = rand;
        this.coloana = coloana;
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public int getColoana() {
        return coloana;
    }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }
}
