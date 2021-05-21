package Teatrus.client;

import Teatrus.model.Spectacol;
import Teatrus.services.TeatrusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeatrusSpectacoleFXML extends UnicastRemoteObject implements Initializable, Serializable {
    HBox hb = new HBox();
    List<Spectacol> spectacole=StartApp.serverOperations.getAllShows();
    @FXML
    ScrollPane scrollPane;
    @FXML
    GridPane grid;
    @FXML
    Button backButton;
    @FXML
    ImageView pic;
    @FXML
    Image image;
    @FXML
    String id;
    public TeatrusSpectacoleFXML() throws RemoteException {
    }

    protected TeatrusSpectacoleFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusSpectacoleFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {



            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            // gridpane settings
            // setting exterior grid padding
            grid.setPadding(new Insets(7,7,7,7));
            // setting interior grid padding
            grid.setHgap(10);
            grid.setVgap(10);
            // grid.setGridLinesVisible(true);

            int rows = (spectacole.size() / 3) + 1;
            int columns = 3;
            int imageIndex = 0;

            for (int i = 0 ; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (imageIndex < spectacole.size()) {
                        addImage(imageIndex, j, i);
                        imageIndex++;
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void addImage(int index, int colIndex, int rowIndex) {
        image = new Image(spectacole.get(index).getAdresaAfis());
        pic = new ImageView();
        pic.setFitWidth(260);
        pic.setFitHeight(220);
        pic.setImage(image);
        pic.setId(spectacole.get(index).getTitlu());
        hb.getChildren().add(pic);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
        grid.getChildren().addAll(pic);

        pic.setOnMouseClicked(e -> {
            try {
                // storing the selected film to customise the newly created scene
                StartApp.showTitle=spectacole.get(index).getTitlu();
                StartApp.selectedShow=pic;
                SceneCreator.launchScene("/Teatrus/Teatrus-SpectacolSelectat.fxml","Teatrus - Spectacol selectat");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    public void back(ActionEvent event) throws TeatrusException,IOException {
        if (StartApp.user.getTipUtilizator()==1){
            SceneCreator.launchScene("/Teatrus/Teatrus-PanouCentralAdministrator.fxml","Teatrus - Panou central");
        }
        else{
            SceneCreator.launchScene("/Teatrus/Teatrus-PanouCentralClient.fxml","Teatrus - Panou central");
        }

    }
}
