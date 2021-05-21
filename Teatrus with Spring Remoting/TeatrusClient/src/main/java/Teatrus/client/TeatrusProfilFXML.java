package Teatrus.client;

import Teatrus.model.User;
import Teatrus.services.ITeatrusObserver;
import Teatrus.services.TeatrusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class TeatrusProfilFXML extends UnicastRemoteObject implements Initializable, Serializable, ITeatrusObserver {
    @FXML
    public Label lblFullName;
    public Label lblEmail;
    public Label lblStatut;
    public Button backButton;
    public TextField updateFirstName;
    public TextField updateLastName;
    public TextField updateEmail;
    public TextField updatePassword;

    public TeatrusProfilFXML() throws RemoteException {
    }

    protected TeatrusProfilFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusProfilFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblFullName.setText(StartApp.user.getNume()+" "+StartApp.user.getPrenume());
        lblEmail.setText(StartApp.user.getEmail());
        if (StartApp.user.getTipUtilizator()==1){
            lblStatut.setText("Administrator");
        }
        else{
            lblStatut.setText("Utilizator");
        }

    }

    @FXML
    public void back(ActionEvent event) throws TeatrusException, IOException {
        if (StartApp.user.getTipUtilizator()==1){
            SceneCreator.launchScene("/Teatrus/Teatrus-PanouCentralAdministrator.fxml","Teatrus - Panou central");
        }
        else{
            SceneCreator.launchScene("/Teatrus/Teatrus-PanouCentralClient.fxml","Teatrus - Panou central");
        }

    }
   /* @FXML
    public void modifyName(KeyEvent keyEvent) {
        lblFullName.setText(updateFirstName.getText()+" "+updateLastName.getText());
    }

    @FXML
    public void modifySurname(KeyEvent keyEvent) {
        lblFullName.setText(updateFirstName.getText()+" "+updateLastName.getText());
    }

    @FXML
    public void modifyEmail(KeyEvent keyEvent) {
        lblEmail.setText(updateEmail.getText());
    }
*/
    public void updateFields(User utilizator){
        lblFullName.setText(utilizator.getNume()+" "+utilizator.getPrenume());
        lblEmail.setText(utilizator.getEmail());
    }

    @FXML
    public void saveData(ActionEvent event) throws TeatrusException,IOException {
        try {
        if (updateFirstName.getText().equals("")||updateFirstName.getText().equals("")||updateFirstName.getText().equals("")||updateFirstName.getText().equals("")){
            throw new TeatrusException("Datele introduse nu sunt corespunzatoare !");
        }
            StartApp.user.setNume(updateFirstName.getText());
            StartApp.user.setPrenume(updateLastName.getText());
            StartApp.user.setEmail(updateEmail.getText());
            StartApp.user.setParola(updatePassword.getText());


                StartApp.serverOperations.updateUserProfile(StartApp.user,this);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Teatrus");
                alert.setHeaderText("Modificarea contului s-a produs cu succes !");
                alert.setContentText("Datele introduse au fost modificate in mod crespunzator in baza de date !");
                alert.showAndWait();

                updateFields(StartApp.user);

            }
            catch (Exception exception){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Teatrus");
                alert.setHeaderText("Modificarea contului nu a avut loc !");
                alert.setContentText("Datele introduse nu sunt corespunzatoare !");
                alert.showAndWait();
                System.out.println(exception.getMessage());
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


                lblFullName.setText(u.getNume()+" "+u.getPrenume());
                lblEmail.setText(u.getEmail());

    }


}
