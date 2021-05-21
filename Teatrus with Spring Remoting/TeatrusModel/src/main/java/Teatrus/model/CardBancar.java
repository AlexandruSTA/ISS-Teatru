package Teatrus.model;

import java.io.Serializable;
import java.util.Date;

public class CardBancar implements Serializable {
    private int idCard,idUser;
    private String titular,numarCard;
    private Date valabilitate;

    public CardBancar() {
        this.idCard=this.idUser=0;
        this.titular=this.numarCard="";
        this.valabilitate=new Date();
    }

    public CardBancar(int idCard, int idUser, String titular, String numarCard, Date valabilitate) {
        this.idCard = idCard;
        this.idUser = idUser;
        this.titular = titular;
        this.numarCard = numarCard;
        this.valabilitate = valabilitate;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumarCard() {
        return numarCard;
    }

    public void setNumarCard(String numarCard) {
        this.numarCard = numarCard;
    }

    public Date getValabilitate() {
        return valabilitate;
    }

    public void setValabilitate(Date valabilitate) {
        this.valabilitate = valabilitate;
    }
}
