package Teatrus.client;

import Teatrus.model.Spectacol;
import Teatrus.model.User;
import Teatrus.services.ITeatrusObserver;
import Teatrus.services.TeatrusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.List;
import java.util.ResourceBundle;

public class TeatrusSpectacolSelectatFXML extends UnicastRemoteObject implements Initializable, Serializable, ITeatrusObserver {

    @FXML
    public ImageView selectedPoster;
    public Text title;
    public Text description;
    public Text startDate;
    public Text endDate;
    public Text time;
    public Button bookButton;
    public Button backButton;
    public Button deleteShowButton;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public TeatrusSpectacolSelectatFXML() throws RemoteException {
    }

    protected TeatrusSpectacolSelectatFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusSpectacolSelectatFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Spectacol spectacol=StartApp.serverOperations.findSpectacol(StartApp.showTitle);
        if (StartApp.user.getTipUtilizator()==2){
            deleteShowButton.setVisible(false);
            bookButton.setVisible(true);
        }
        else{
            deleteShowButton.setVisible(true);
            bookButton.setVisible(false);
        }
        title.setText(spectacol.getTitlu());
        description.setText(spectacol.getDescriere());
        startDate.setText(dateFormat.format(spectacol.getDataInceput()));
        endDate.setText(dateFormat.format(spectacol.getDataSfarsit()));

        try {
            //String path = URLDecoder.decode(StartApp.getPath() + "Images/Spectacole/", "UTF-8");
            //File imgFile = new File(path + title.getText() + ".jpg");
            //File imgFile=new File("https://cdn4.libris.ro/img/pozeprod/59/1002/BC/1162339.jpg");
            //Image img = SwingFXUtils.toFXImage(ImageIO.read(imgFile), null);
            Image img=new Image(spectacol.getAdresaAfis());
            selectedPoster.setImage(img);
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
        }
        time.setText("20:00");
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
    public void back(ActionEvent event) throws TeatrusException,IOException {
        SceneCreator.launchScene("/Teatrus/Teatrus-Spectacole.fxml","Teatrus - Spectacole");
    }

    @FXML
    public void deleteShow(ActionEvent event) throws TeatrusException, IOException {
        try {
            Spectacol spectacol = StartApp.serverOperations.findSpectacol(StartApp.showTitle);
            StartApp.serverOperations.deleteSpectacol(spectacol);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teatrus");
            alert.setHeaderText("Stergerea spectacolului cu id-ul "+spectacol.getIdSpectacol()+" a fost realizata cu succes !");
            alert.setContentText("Spectacolul a fost sters cu succes din baza de date !");
            alert.showAndWait();
            SceneCreator.launchScene("/Teatrus/Teatrus-Spectacole.fxml","Teatrus - Spectacole");

        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    public void booking(ActionEvent event) throws TeatrusException,IOException {
        SceneCreator.launchScene("/Teatrus/Teatrus-Achizitionare.fxml","Teatrus - Achizitionare");
    }
}
