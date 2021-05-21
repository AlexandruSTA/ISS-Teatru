package Teatrus.services;


import Teatrus.model.*;

import java.util.List;

public interface ITeatrusServices {
    void login(User u,ITeatrusObserver client) throws TeatrusException;
    void logout(User u,ITeatrusObserver client) throws TeatrusException;
    void updateUserProfile(User utilizatorActualizat, ITeatrusObserver client) throws TeatrusException;
    List<User> getAllUsers();
    List<Spectacol> getAllShows();
    void addSpectacol(Spectacol spectacol);
    void deleteSpectacol(Spectacol spectacol);
    void updateUser(int id,User user);
    User findUser(User u);
    Spectacol findSpectacol(String titlu);



}
