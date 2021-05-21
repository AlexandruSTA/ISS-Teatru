package Teatrus.client;

import javafx.fxml.Initializable;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class TeatrusIstoricSpectacoleFXML extends UnicastRemoteObject implements Initializable, Serializable {
    public TeatrusIstoricSpectacoleFXML() throws RemoteException {
    }

    protected TeatrusIstoricSpectacoleFXML(int port) throws RemoteException {
        super(port);
    }

    protected TeatrusIstoricSpectacoleFXML(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
