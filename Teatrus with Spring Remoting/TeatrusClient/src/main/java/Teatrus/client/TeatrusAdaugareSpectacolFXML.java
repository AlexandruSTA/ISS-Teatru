package Teatrus.client;

import Teatrus.model.Spectacol;
import Teatrus.services.TeatrusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class TeatrusAdaugareSpectacolFXML extends UnicastRemoteObject implements Initializable, Serializable {
    @FXML
    public TextArea updateDescription;
    public TextField updateTitle;
    public DatePicker updateShowEndDate;
    public ComboBox updateHour;
    public TextField updateImage;
    public DatePicker updateShowStartDate;
    public Text lblStartDate;
    public Text lblEndDate;
    public Text lblHour;
    public ImageView uploadedFilmPoster;
    public Text lblTitle;
    public Text lblDescription;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public Button backButton;
    public Button btnAdd;
    public ToggleButton btnShows;

    public TeatrusAdaugareSpectacolFXML() throws RemoteException {
    }

    protected TeatrusAdaugareSpectacolFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusAdaugareSpectacolFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateHour.getItems().add("14:00");
        updateHour.getItems().add("16:00");
        updateHour.getItems().add("18:00");
        updateHour.getItems().add("20:00");
        updateHour.getItems().add("22:00");
    }
    @FXML
    public void modifyDescription(KeyEvent keyEvent) {
        lblDescription.setText(updateDescription.getText());
        
    }
    
    @FXML
    public void modifyTitle(KeyEvent keyEvent) {
        lblTitle.setText(updateTitle.getText());
    }

    @FXML
    public void modifyPoster(MouseEvent mouseEvent) {
        try {
            uploadedFilmPoster.setImage(new Image(updateImage.getText()));
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
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

    @FXML
    public void modifyHour(ActionEvent event) {
        lblHour.setText(updateHour.getValue().toString());
    }

    @FXML
    public void modifyStartDate(ActionEvent event) {
        LocalDate localDate = updateShowStartDate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        lblStartDate.setText(dateFormat.format(date));
    }

    @FXML
    public void modifyEndDate(ActionEvent event) {
        LocalDate localDate = updateShowEndDate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        lblEndDate.setText(dateFormat.format(date));
    }

    @FXML
    public void addShow(ActionEvent event) throws TeatrusException,IOException {
        try {
        Spectacol spectacol=new Spectacol();
        spectacol.setTitlu(lblTitle.getText());
        spectacol.setDescriere(lblDescription.getText());
        spectacol.setAdresaAfis(updateImage.getText());

        LocalDate localDateStart = updateShowStartDate.getValue();
        Instant instantStart = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
        Date dataInceput = Date.from(instantStart);
        spectacol.setDataInceput(dataInceput);

        LocalDate localDateEnd = updateShowEndDate.getValue();
        Instant instantEnd = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
        Date dataSfarsit = Date.from(instantEnd);
        spectacol.setDataSfarsit(dataSfarsit);


            StartApp.serverOperations.addSpectacol(spectacol);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teatrus");
            alert.setHeaderText("Adaugarea spectacolului cu titlul <<"+ spectacol.getTitlu()+">> a fost realizata cu succes !");
            alert.setContentText("Spectacolul a fost adaugat cu succes in baza de date !");
            alert.showAndWait();
            SceneCreator.launchScene("/Teatrus/Teatrus-Spectacole.fxml","Teatrus - Spectacole");
        }
        catch (Exception exc){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teatrus");
            alert.setHeaderText("Adaugarea spectacolului nu a fost realizata cu succes !");
            alert.setContentText("Spectacolul nu a fost adaugat cu succes din baza de date !");
            alert.showAndWait();
        }




    }
    @FXML
    public void viewShows(ActionEvent event) throws TeatrusException,IOException{
        SceneCreator.launchScene("/Teatrus/Teatrus-Spectacole.fxml","Teatrus - Spectacole");
    }
}
