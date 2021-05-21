package Teatrus.client;

import Teatrus.model.User;
import Teatrus.services.ITeatrusObserver;
import Teatrus.services.TeatrusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;

public class TeatrusPanouCentralClientFXML extends UnicastRemoteObject implements Initializable, Serializable, ITeatrusObserver {
    @FXML
    public Button btnLogout;
    public Label lastNameLabel;
    public Label firstNameLabel;
    public Label lblWelcome;
    public Button btnEdit;

    public TeatrusPanouCentralClientFXML() throws RemoteException {
    }

    protected TeatrusPanouCentralClientFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusPanouCentralClientFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameLabel.setText(StartApp.user.getNume());
        lastNameLabel.setText(StartApp.user.getPrenume());
        lblWelcome.setText("Bine ai revenit, "+StartApp.user.getPrenume()+" :)");
    }

    @Override
    public void UserLoggedIn(User u) throws TeatrusException, RemoteException {
        System.out.println(u.getNumeUtilizator()+"just logged in !");
    }

    @Override
    public void UserLoggedOut(User u) throws TeatrusException, RemoteException {

    }


    @Override
    public void ActualizareUser(User u) throws TeatrusException, RemoteException {

    }


    public void logoutOperation(){
        try{
            StartApp.serverOperations.logout(StartApp.user,this);
        }
        catch (TeatrusException exc){
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    public void logout(ActionEvent event) throws TeatrusException {
        logoutOperation();
        System.exit(0);
    }

    @FXML
    public void viewShows(ActionEvent event) throws TeatrusException, IOException {
        SceneCreator.launchScene("/Teatrus/Teatrus-Spectacole.fxml","Teatrus - Spectacole");
    }

    public void edit(ActionEvent event) throws TeatrusException, IOException{
        SceneCreator.launchScene("/Teatrus/Teatrus-Profil.fxml","Teatrus - Profil");
    }
}
