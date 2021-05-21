package Teatrus.persistence.repository.jdbc;

import Teatrus.model.CardBancar;
import Teatrus.model.Loc;
import Teatrus.model.Pozitie;
import Teatrus.model.StatusLoc;
import Teatrus.persistence.IRepositoryLoc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class RepositoryLoc implements IRepositoryLoc {
    private JDBCUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public RepositoryLoc(Properties props){
        logger.info("Initializing RepositoryLoc with properties: {} ",props);
        dbUtils=new JDBCUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Loc")) {
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
    public void save(Loc entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Loc(idLoc,idSpectacol,rand,coloana,pret) values (?,?,?,?,?)")){
            preStmt.setInt(1,size()+1);
            preStmt.setInt(2,entity.getIdSpectacol());
            preStmt.setInt(3, entity.getPozitie().getRand());
            preStmt.setInt(4,entity.getPozitie().getColoana());
            preStmt.setInt(5,entity.getPret());
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
        try(PreparedStatement preStmt=con.prepareStatement("delete from Loc where idLoc=?")){
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
    public void update(Integer integer, Loc entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Loc set idSpectacol=?, rand=?, coloana=?, pret=? where idLoc=?")){
            preStmt.setInt(1,entity.getIdSpectacol());
            preStmt.setInt(2, entity.getPozitie().getRand());
            preStmt.setInt(3,entity.getPozitie().getColoana());
            preStmt.setInt(4,entity.getPret());
            preStmt.setInt(5,integer);
            int result=preStmt.executeUpdate();
        }
        catch(SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
    }

    @Override
    public Loc findOne(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Loc where idLoc=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    int idLoc=result.getInt("idLoc");
                    int idSpectacol=result.getInt("idSpectacol");
                    int rand=result.getInt("rand");
                    int coloana=result.getInt("coloana");
                    int pret=result.getInt("pret");
                    Pozitie pozitie=new Pozitie(rand,coloana);
                    Loc loc=new Loc(idLoc,idSpectacol,pozitie,pret, StatusLoc.Liber);
                    logger.traceExit(loc);
                    return loc;
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
    public ArrayList<Loc> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        ArrayList<Loc> locuri=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Loc ")){

            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()){
                    int idLoc=result.getInt("idLoc");
                    int idSpectacol=result.getInt("idSpectacol");
                    int rand=result.getInt("rand");
                    int coloana=result.getInt("coloana");
                    int pret=result.getInt("pret");
                    Pozitie pozitie=new Pozitie(rand,coloana);
                    Loc loc=new Loc(idLoc,idSpectacol,pozitie,pret, StatusLoc.Liber);
                    logger.traceExit(loc);
                    locuri.add(loc);
                }
            }
        }
        catch (SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
        return locuri;
    }
}
