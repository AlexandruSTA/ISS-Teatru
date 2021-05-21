package Teatrus.client;

import Teatrus.model.User;
import Teatrus.services.ITeatrusObserver;
import Teatrus.services.TeatrusException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class TeatrusInregistrareFXML extends UnicastRemoteObject implements Serializable, Initializable, ITeatrusObserver {
    public TextField saveFirstname;
    public TextField saveLastname;
    public TextField saveEmail;
    public Button btnSaveProfileData;
    public PasswordField savePassword;
    public TextField saveUsername;

    public TeatrusInregistrareFXML() throws RemoteException {
    }

    protected TeatrusInregistrareFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusInregistrareFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


    public void saveData(ActionEvent event) {
        try {
            User user = new User();
            user.setNume(saveFirstname.getText());
            user.setPrenume(saveLastname.getText());
            user.setEmail(saveEmail.getText());
            user.setNumeUtilizator(saveUsername.getText());
            user.setParola(savePassword.getText());
            user.setTipUtilizator(2);

            StartApp.serverOperations.addUser(user);
            SceneCreator.launchScene("/Teatrus/Teatrus-Autentificare.fxml","Teatrus - Autentificare");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teatrus");
            alert.setHeaderText("Inregistrarea s-a realizat cu succes !");
            alert.setContentText("Contul a fost adaugat cu succes !");
            alert.showAndWait();
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
        }

    }

    public void back(ActionEvent event) {
    }
}
