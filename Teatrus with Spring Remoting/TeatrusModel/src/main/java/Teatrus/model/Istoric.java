package Teatrus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Istoric implements Serializable {
    private int idIstoric;
    private List<Spectacol> spectacole;
    private int idUser;

    public Istoric(){
        this.idIstoric=0;
        this.spectacole=new ArrayList<Spectacol>();
        this.idUser=0;
    }

    public Istoric(int idIstoric,List<Spectacol> spectacole,int idUser) {
        this.idIstoric=idIstoric;
        this.spectacole = spectacole;
        this.idUser=idUser;
    }

    public int getIdIstoric() {
        return idIstoric;
    }

    public void setIdIstoric(int idIstoric) {
        this.idIstoric = idIstoric;
    }

    public List<Spectacol> getSpectacole() {
        return spectacole;
    }

    public void setSpectacole(List<Spectacol> spectacole) {
        this.spectacole = spectacole;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
