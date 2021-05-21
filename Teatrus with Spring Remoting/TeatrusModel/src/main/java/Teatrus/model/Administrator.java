package Teatrus.model;

import java.io.Serializable;

public class Administrator extends User implements Serializable{
    public Administrator() {
    }

    public Administrator(int idUser, String nume, String prenume, String numeUtilizator, String parola, int tipUtilizator) {
        super(idUser, nume, prenume, numeUtilizator, parola,tipUtilizator);
    }


}
