package Teatrus.server;

import Teatrus.model.*;
import Teatrus.persistence.*;
import Teatrus.services.ITeatrusObserver;
import Teatrus.services.ITeatrusServices;
import Teatrus.services.TeatrusException;


import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TeatrusServices implements ITeatrusServices {

    private IRepositoryCardBancar repositoryCardBancar;
    private IRepositoryIstoric repositoryIstoric;
    private IRepositoryLoc repositoryLoc;
    private IRepositoryRezervare repositoryRezervare;
    private IRepositorySpectacol repositorySpectacol;
    private IRepositoryUser repositoryUser;
    private Map<Integer,ITeatrusObserver> loggedUsers;


    public TeatrusServices(IRepositoryCardBancar repositoryCardBancar, IRepositoryIstoric repositoryIstoric, IRepositoryLoc repositoryLoc, IRepositoryRezervare repositoryRezervare, IRepositorySpectacol repositorySpectacol, IRepositoryUser repositoryUser) {
        this.repositoryCardBancar = repositoryCardBancar;
        this.repositoryIstoric = repositoryIstoric;
        this.repositoryLoc = repositoryLoc;
        this.repositoryRezervare = repositoryRezervare;
        this.repositorySpectacol = repositorySpectacol;
        this.repositoryUser = repositoryUser;
        loggedUsers=new ConcurrentHashMap<>();
    }


    public synchronized void login(User u, ITeatrusObserver client) throws TeatrusException {

        if (findUser(u)!=null){
            if (loggedUsers.get(u.getIdUser())!=null) throw new TeatrusException("Utilizatorul este logat deja !");
            loggedUsers.put(u.getIdUser(),client);
            notifyUserLoggedIn(u);
        }
        else
            throw new TeatrusException("Authentication failed !");
    }



    public synchronized void logout(User u, ITeatrusObserver client) throws TeatrusException{
        ITeatrusObserver localClient=loggedUsers.remove(u.getIdUser());
        if (localClient==null){
            throw new TeatrusException("Employee "+ u.getNume()+" is no longer logged !");
        }
        notifyUserLoggedOut(u);

    }

    private void notifyUserLoggedIn(User u){
        List<User> users= repositoryUser.findAll();
        System.out.println("Logged "+users);

        ExecutorService executor= Executors.newFixedThreadPool(users.size());
        for (User user:users){
            ITeatrusObserver teatrusClient=loggedUsers.get(user.getIdUser());
            if ((teatrusClient!=null) && (user.getIdUser()!=u.getIdUser()))
                executor.execute(()->{
                    try{
                        System.out.println("Notifying ["+user.getNume()+"] user ["+u.getNume()+"] just logged in.");
                        teatrusClient.UserLoggedIn(u);
                    }
                    catch (TeatrusException|RemoteException exc){
                        System.out.println(exc.getMessage());
                    }
                });
        }
        executor.shutdown();
    }

    private void notifyUserLoggedOut(User u){
        List<User> users= repositoryUser.findAll();
        System.out.println("Logged "+users);

        ExecutorService executor= Executors.newFixedThreadPool(users.size());
        for (User user:users){
            ITeatrusObserver teatrusClient=loggedUsers.get(user.getIdUser());
            if ((teatrusClient!=null) && (user.getIdUser()!=u.getIdUser()))
                executor.execute(()->{
                    try{
                        System.out.println("Notifying ["+user.getNume()+"] user ["+u.getNume()+"] just logged out.");
                        teatrusClient.UserLoggedOut(u);
                    }
                    catch (TeatrusException|RemoteException exc){
                        System.out.println(exc.getMessage());
                    }
                });
        }
        executor.shutdown();
    }





    @Override
    public synchronized void updateUserProfile(User utilizatorActualizat, ITeatrusObserver client) throws TeatrusException {
        System.out.println("TEST IN UPDATE USER PROFILE =====>"+utilizatorActualizat.getNume());
        updateUser(utilizatorActualizat.getIdUser(),utilizatorActualizat);
        notifyUpdatedUser(utilizatorActualizat);
    }



    private void notifyUpdatedUser(User utilizatorActualizat){
        List<User> utilizatori=repositoryUser.findAll();
        System.out.println("Update User for employees:  "+utilizatori);

        ExecutorService executor= Executors.newFixedThreadPool(utilizatori.size());
        for (User user:utilizatori){
            ITeatrusObserver teatrusClient=loggedUsers.get(user.getIdUser());
            if (teatrusClient!=null)
                executor.execute(()->{
                    try{
                        System.out.println("Updated utilizator in Services for "+user.getIdUser()+" !");
                        System.out.println("TEATRUS CLIENT ====>"+teatrusClient);
                        teatrusClient.ActualizareUser(utilizatorActualizat);
                    }
                    catch (TeatrusException|RemoteException exc){
                        System.out.println(exc.getMessage());
                    }
                });
        }
        executor.shutdown();
    }



    @Override
    public synchronized List<Spectacol> getAllShows() {
        return repositorySpectacol.findAll();
    }

    @Override
    public synchronized List<User> getAllUsers() {
        return repositoryUser.findAll();
    }



    @Override
    public void addSpectacol(Spectacol spectacol) {
        repositorySpectacol.save(spectacol);
    }

    @Override
    public void addUser(User user) {
        repositoryUser.save(user);
    }

    @Override
    public void deleteSpectacol(Spectacol spectacol) {
        repositorySpectacol.delete(spectacol.getIdSpectacol());
    }

    @Override
    public void deleteUser(User user) {
        repositoryUser.delete(user.getIdUser());
    }

    @Override
    public void updateUser(int id, User user) {
        repositoryUser.update(id,user);
    }


    @Override
    public User findUser(User u) {
        for (User user:getAllUsers()){
            if (user.getNumeUtilizator().equals(u.getNumeUtilizator())&&user.getParola().equals(u.getParola())){
                return user;
            }
        }
        return null;
    }

    @Override
    public Spectacol findSpectacol(String titlu) {
        for (Spectacol spectacol:getAllShows()){
            System.out.println("TITLU====>"+spectacol.getTitlu());
            if (spectacol.getTitlu().equals(titlu)){

                return spectacol;
            }
        }
        return null;
    }
}
