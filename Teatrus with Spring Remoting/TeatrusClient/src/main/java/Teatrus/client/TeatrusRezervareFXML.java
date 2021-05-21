package Teatrus.client;

import Teatrus.model.Spectacol;
import Teatrus.model.User;
import Teatrus.services.ITeatrusObserver;
import Teatrus.services.TeatrusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;

public class TeatrusRezervareFXML extends UnicastRemoteObject implements Serializable, Initializable, ITeatrusObserver {
    public Text showSummary;
    public Text dateSummary;
    public Text timeSummary;
    public Text seatSummary;
    public ToggleButton closeButton;
    public Label lblSeats;

    public TeatrusRezervareFXML() throws RemoteException {
    }

    protected TeatrusRezervareFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusRezervareFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Spectacol spectacol=StartApp.serverOperations.findSpectacol(StartApp.showTitle);
        this.showSummary.setText(spectacol.getTitlu());
        this.dateSummary.setText(spectacol.getDataInceput().toString());
        this.lblSeats.setText(Integer.toString(StartApp.numberOfPlaces));
    }

    @Override
    public void UserLoggedIn(User u) throws TeatrusException, RemoteException {

    }

    @Override
    public void UserLoggedOut(User u) throws TeatrusException, RemoteException {

    }



    @Override
    public void ActualizareUser(User u) throws TeatrusException, RemoteException {

    }


    @FXML
    public void closeStage(ActionEvent event) throws TeatrusException,IOException {
        SceneCreator.launchScene("/Teatrus/Teatrus-Spectacole.fxml","Teatrus - Spectacole");
    }
}
