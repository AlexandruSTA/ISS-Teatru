package Teatrus.client;

import Teatrus.model.User;
import Teatrus.services.ITeatrusServices;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class StartApp extends Application {
    static Parent root;
    static Stage primaryStage;
    static StartApp m=null;
    static ITeatrusServices serverOperations=null;
    static User user=new User();
    static String showTitle=new String();
    static ImageView selectedShow=new ImageView();
    static int numberOfPlaces=0;

    static String getPath() {

        String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
        if (path.contains("TeatrusClient"))
            path = path.split("TeatrusClient")[0];

        return path;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            ITeatrusServices server=(ITeatrusServices)factory.getBean("teatrusService");
            System.out.println("Obtained a reference to remote teatrus server !");
            serverOperations=server;


            root=FXMLLoader.load(getClass().getResource("/Teatrus/Teatrus-Autentificare.fxml"));
            StartApp.primaryStage=primaryStage;
            primaryStage.setTitle("Autentificare");
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (Exception exc){
            System.err.println("Initialization exception "+exc);
            exc.printStackTrace();
        }
    }

    static Parent getRoot() {

        return root;
    }

    static void setRoot(Parent root) {

        StartApp.root = root;
    }

    static Stage getStage() {

        return primaryStage;
    }

    static void setStage(Stage stage) {

        StartApp.primaryStage = stage;
    }

}
