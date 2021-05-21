package Teatrus.client;

import Teatrus.model.Spectacol;
import Teatrus.services.TeatrusException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class TeatrusAchizitionareFXML extends UnicastRemoteObject implements Initializable, Serializable {

    public int numberOfPlaces=0;
    public Label totalSeatsLabel;
    public Label bookedSeatsLabel;
    public Label availableSeatsLabel;
    public ComboBox showDropDownList;
    public DatePicker datePicker;
    public ComboBox timeDropDownList;
    public Button backButton;
    public Button btnBook;
    @FXML
    FontAwesomeIcon place11;
    @FXML
    FontAwesomeIcon place12;
    @FXML
    FontAwesomeIcon place13;
    @FXML
    FontAwesomeIcon place14;
    @FXML
    FontAwesomeIcon place15;
    @FXML
    FontAwesomeIcon place16;
    @FXML
    FontAwesomeIcon place21;
    @FXML
    FontAwesomeIcon place22;
    @FXML
    FontAwesomeIcon place23;
    @FXML
    FontAwesomeIcon place24;
    @FXML
    FontAwesomeIcon place25;
    @FXML
    FontAwesomeIcon place26;
    @FXML
    FontAwesomeIcon place31;
    @FXML
    FontAwesomeIcon place32;
    @FXML
    FontAwesomeIcon place33;
    @FXML
    FontAwesomeIcon place34;
    @FXML
    FontAwesomeIcon place35;
    @FXML
    FontAwesomeIcon place36;

    public TeatrusAchizitionareFXML() throws RemoteException {
    }

    protected TeatrusAchizitionareFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusAchizitionareFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Spectacol spectacol=StartApp.serverOperations.findSpectacol(StartApp.showTitle);
        showDropDownList.getItems().clear();
        showDropDownList.getItems().add(spectacol.getTitlu());
        Date date=spectacol.getDataInceput();
        datePicker.setValue(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        timeDropDownList.getItems().add("20:00");
    }

    @FXML
    public void back(ActionEvent event) throws TeatrusException, IOException {
        SceneCreator.launchScene("/Teatrus/Teatrus-Spectacole.fxml","Teatrus - Spectacole");
    }

    @FXML
    public void selectSeat(MouseEvent mouseEvent) {
        if (((Node) mouseEvent.getSource()).getStyle()
                .equals("fill:GREY;")) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "The seat " + ((Node) mouseEvent.getSource()).getId() + " is already booked!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        } else {
            // turning seat back to black if it is red - unselecting it
            if (((Node) mouseEvent.getSource()).getStyle()
                    .equals("-fx-fill:red; -fx-font-family: FontAwesome;")) {
                ((Node) mouseEvent.getSource())
                        .setStyle("-fx-fill:black;");
                //Main.getSelectedSeats().remove(((Node) e.getSource()).getId());
            }
            // turning seat red if it is black - selecting it
            else {
                numberOfPlaces++;
                ((Node) mouseEvent.getSource())
                        .setStyle("-fx-fill:red;-fx-font-family: FontAwesome; -fx-font-size: 4em;");
                //Main.getSelectedSeats().add(((Node) e.getSource()).getId());
            }
        }
    }
    @FXML
    public void book(ActionEvent event) throws TeatrusException,IOException {

        if (numberOfPlaces>0){
            StartApp.numberOfPlaces=numberOfPlaces;
            SceneCreator.launchScene("/Teatrus/Teatrus-Rezervare.fxml","Teatrus - Rezervare");
        }
    }
}
