package Teatrus.client;

import java.io.IOException;

import Teatrus.services.TeatrusException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;


public class SceneCreator {

    // launching the new scene based on the .fxml file name passed in the argument as a String variable
    // building the scene and setting the value for the instance variable loader
    public static void launchScene (String sceneName,String title) throws IOException {

        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(StartApp.class.getResource(sceneName));
        StartApp.setRoot(loader.load());
        Scene scene = new Scene(StartApp.getRoot());
        StartApp.getStage().setScene(scene);
        StartApp.getStage().setTitle(title);
        StartApp.getStage().show();
        StartApp.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    StartApp.serverOperations.logout(StartApp.user,null);
                } catch (TeatrusException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}
