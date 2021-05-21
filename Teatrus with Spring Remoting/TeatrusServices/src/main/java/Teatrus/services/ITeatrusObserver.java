package Teatrus.services;

import Teatrus.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITeatrusObserver extends Remote {

    void UserLoggedIn(User u) throws TeatrusException,RemoteException;
    void UserLoggedOut(User u) throws TeatrusException,RemoteException;
    void ActualizareUser(User u) throws TeatrusException,RemoteException;
}
