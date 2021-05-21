package Teatrus.persistence.repository.jdbc;

import Teatrus.model.Loc;
import Teatrus.model.Pozitie;
import Teatrus.model.Rezervare;
import Teatrus.model.StatusLoc;
import Teatrus.persistence.IRepositoryRezervare;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class RepositoryRezervare implements IRepositoryRezervare {
    private JDBCUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public RepositoryRezervare(Properties props){
        logger.info("Initializing RepositoryRezervare with properties: {} ",props);
        dbUtils=new JDBCUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Rezervare")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        return 0;
    }

    @Override
    public void save(Rezervare entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Rezervare(idRezervare,idClient,idSpectacol,numarLocuri) values (?,?,?,?)")){
            preStmt.setInt(1,size()+1);
            preStmt.setInt(2,entity.getIdClient());
            preStmt.setInt(3, entity.getIdSpectacol());
            preStmt.setInt(4,entity.getNumarLocuri());
            int result=preStmt.executeUpdate();
        }
        catch(SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Rezervare where idRezervare=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }
        catch(SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Rezervare entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Rezervare set idClient=?, idSpectacol=?, numarLocuri=? where idRezervare=?")){
            preStmt.setInt(1,entity.getIdClient());
            preStmt.setInt(2, entity.getIdSpectacol());
            preStmt.setInt(3,entity.getNumarLocuri());
            preStmt.setInt(4,integer);
            int result=preStmt.executeUpdate();
        }
        catch(SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
    }

    @Override
    public Rezervare findOne(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Rezervare where idRezervare=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    int idRezervare=result.getInt("idRezervare");
                    int idClient=result.getInt("idClient");
                    int idSpectacol=result.getInt("idSpectacol");
                    int numarLocuri=result.getInt("numarLocuri");
                    Rezervare rezervare=new Rezervare(idRezervare,idClient,idSpectacol,numarLocuri);
                    logger.traceExit(rezervare);
                    return rezervare;
                }
            }
        }
        catch (SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public ArrayList<Rezervare> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        ArrayList<Rezervare> rezervari=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Rezervare ")){

            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()){
                    int idRezervare=result.getInt("idRezervare");
                    int idClient=result.getInt("idClient");
                    int idSpectacol=result.getInt("idSpectacol");
                    int numarLocuri=result.getInt("numarLocuri");
                    Rezervare rezervare=new Rezervare(idRezervare,idClient,idSpectacol,numarLocuri);
                    logger.traceExit(rezervare);
                    rezervari.add(rezervare);
                }
            }
        }
        catch (SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
        return rezervari;
    }
}
