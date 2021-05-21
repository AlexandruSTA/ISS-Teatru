package Teatrus.model;

import java.io.Serializable;

public class ClientTeatrus extends User implements Serializable {
    private CardBancar cardBancar;
    private Istoric istoric;

    public ClientTeatrus(){
        super();
        this.cardBancar=new CardBancar();
        this.istoric=new Istoric();
    }

    public ClientTeatrus(CardBancar cardBancar, Istoric istoric) {
        this.cardBancar = cardBancar;
        this.istoric = istoric;
    }

    public ClientTeatrus(int idUser, String nume, String prenume, String numeUtilizator, String parola, int tipUtilizator, CardBancar cardBancar, Istoric istoric) {
        super(idUser, nume, prenume, numeUtilizator, parola,tipUtilizator);
        this.cardBancar = cardBancar;
        this.istoric = istoric;
    }

    public CardBancar getCardBancar() {
        return cardBancar;
    }

    public void setCardBancar(CardBancar cardBancar) {
        this.cardBancar = cardBancar;
    }

    public Istoric getIstoric() {
        return istoric;
    }

    public void setIstoric(Istoric istoric) {
        this.istoric = istoric;
    }
}
