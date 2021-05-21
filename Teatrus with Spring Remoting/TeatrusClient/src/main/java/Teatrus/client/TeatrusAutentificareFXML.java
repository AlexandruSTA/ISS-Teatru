package Teatrus.client;

import Teatrus.model.User;
import Teatrus.services.ITeatrusObserver;
import Teatrus.services.TeatrusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;

public class TeatrusAutentificareFXML extends UnicastRemoteObject implements Initializable, Serializable, ITeatrusObserver {


    @FXML
    public TextField lblUser;
    public PasswordField lblPassword;

    public TeatrusAutentificareFXML() throws RemoteException {
    }

    protected TeatrusAutentificareFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusAutentificareFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void login(ActionEvent event) throws IOException {

        try {
            User user=new User();
            user.setNumeUtilizator(lblUser.getText());
            user.setParola(lblPassword.getText());
            user=StartApp.serverOperations.findUser(user);
            StartApp.user=user;
            StartApp.serverOperations.login(user,this);
            if (user.getTipUtilizator()==1){
                SceneCreator.launchScene("/Teatrus/Teatrus-PanouCentralAdministrator.fxml","Teatrus - Panou central");
            }
            else{
                SceneCreator.launchScene("/Teatrus/Teatrus-PanouCentralClient.fxml","Teatrus - Panou central");
            }
        }
        catch(Exception exc){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teatrus");
            alert.setHeaderText("Autentificarea nu s-a realizat cu succes !");
            alert.setContentText("Credentialele sunt incorecte sau utilizatorul este logat deja !");
            alert.showAndWait();
            System.out.println(exc.getMessage());
            }
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

}
