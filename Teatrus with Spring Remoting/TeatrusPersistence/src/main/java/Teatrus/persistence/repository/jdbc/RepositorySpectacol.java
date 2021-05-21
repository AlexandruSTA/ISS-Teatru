package Teatrus.persistence.repository.jdbc;

import Teatrus.model.Rezervare;
import Teatrus.model.Spectacol;
import Teatrus.persistence.IRepositorySpectacol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class RepositorySpectacol implements IRepositorySpectacol {
    private JDBCUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public RepositorySpectacol(Properties props){
        logger.info("Initializing RepositorySpectacol with properties: {} ",props);
        dbUtils=new JDBCUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Spectacol")) {
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
    public void save(Spectacol entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Spectacol(idSpectacol,titlu,descriere,dataDesfasurariiInceput,dataDesfasurariiSfarsit,adresaAfis) values (?,?,?,?,?,?)")){
            preStmt.setInt(1,size()+1);
            preStmt.setString(2,entity.getTitlu());
            preStmt.setString(3, entity.getDescriere());
            preStmt.setDate(4, new java.sql.Date(entity.getDataInceput().getTime()));
            preStmt.setDate(5,new java.sql.Date(entity.getDataSfarsit().getTime()));
            preStmt.setString(6,entity.getAdresaAfis());
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
        try(PreparedStatement preStmt=con.prepareStatement("delete from Spectacol where idSpectacol=?")){
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
    public void update(Integer integer, Spectacol entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Spectacol set titlu=?, descriere=?, dataDesfasurariiInceput=?, dataDesfasurariiInceput=?, adresaAfis=? where idSpecatcol=?")){
            preStmt.setString(1,entity.getTitlu());
            preStmt.setString(2, entity.getDescriere());
            preStmt.setDate(3,(Date) entity.getDataInceput());
            preStmt.setDate(4,(Date) entity.getDataSfarsit());
            preStmt.setString(5,entity.getAdresaAfis());
            preStmt.setInt(6,integer);
            int result=preStmt.executeUpdate();
        }
        catch(SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
    }

    @Override
    public Spectacol findOne(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Spectacol where idSpectacol=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    int idSpectacol=result.getInt("idSpectacol");
                    String titlu=result.getString("titlu");
                    String descriere=result.getString("descriere");
                    java.util.Date dataInceput=result.getTimestamp("dataDesfasurariiInceput");
                    java.util.Date dataSfarsit=result.getTimestamp("dataDesfasurariiSfarsit");
                    String adresaAfis=result.getString("adresaAfis");
                    Spectacol spectacol=new Spectacol(idSpectacol,titlu,descriere,dataInceput);
                    spectacol.setDataSfarsit(dataSfarsit);
                    spectacol.setAdresaAfis(adresaAfis);
                    logger.traceExit(spectacol);
                    return spectacol;
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
    public ArrayList<Spectacol> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        ArrayList<Spectacol> spectacole=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Spectacol")){

            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()){
                    int idSpectacol=result.getInt("idSpectacol");
                    String titlu=result.getString("titlu");
                    String descriere=result.getString("descriere");
                    java.util.Date dataInceput=result.getTimestamp("dataDesfasurariiInceput");
                    java.util.Date dataSfarsit=result.getTimestamp("dataDesfasurariiSfarsit");
                    String adresaAfis=result.getString("adresaAfis");
                    Spectacol spectacol=new Spectacol(idSpectacol,titlu,descriere,dataInceput);
                    spectacol.setDataSfarsit(dataSfarsit);
                    spectacol.setAdresaAfis(adresaAfis);
                    logger.traceExit(spectacol);
                    spectacole.add(spectacol);
                }
            }
        }
        catch (SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
        return spectacole;
    }
}
