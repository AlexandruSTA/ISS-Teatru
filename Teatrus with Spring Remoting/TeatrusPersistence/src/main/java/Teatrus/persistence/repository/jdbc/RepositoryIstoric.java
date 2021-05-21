package Teatrus.persistence.repository.jdbc;

import Teatrus.model.CardBancar;
import Teatrus.model.Istoric;
import Teatrus.model.Spectacol;
import Teatrus.persistence.IRepositoryIstoric;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryIstoric implements IRepositoryIstoric {
    private JDBCUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public RepositoryIstoric(Properties props){
        logger.info("Initializing RepositoryIstoric with properties: {} ",props);
        dbUtils=new JDBCUtils(props);
    }
    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Istoric")) {
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
    public void save(Istoric entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        for(Spectacol spectacol:entity.getSpectacole()) {
            try (PreparedStatement preStmt = con.prepareStatement("insert into Istoric values (?,?,?)")) {
                preStmt.setInt(1, entity.getIdIstoric());
                preStmt.setInt(2, entity.getIdUser());
                preStmt.setInt(3,spectacol.getIdSpectacol());

                int result = preStmt.executeUpdate();
            } catch (SQLException exc) {
                logger.error(exc);
                System.out.println(exc);
            }
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Istoric where idIstoric=?")){
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
    public void update(Integer integer, Istoric entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        for (Spectacol spectacol:entity.getSpectacole()) {
            try (PreparedStatement preStmt = con.prepareStatement("update Istoric set idSpectacol=? where idUser=?")) {
                preStmt.setInt(1, spectacol.getIdSpectacol());
                preStmt.setInt(2, entity.getIdUser());
                int result = preStmt.executeUpdate();
            } catch (SQLException exc) {
                logger.error(exc);
                System.out.println(exc);
            }
        }
        logger.traceExit();
    }

    @Override
    public Istoric findOne(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Istoric where idIstoric=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    int idIstoric=result.getInt("idIstoric");
                    int idUser=result.getInt("idUser");

                    Istoric istoric=new Istoric();
                    istoric.setIdIstoric(idIstoric);
                    istoric.setIdUser(idUser);
                    logger.traceExit(istoric);
                    return istoric;
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
    public ArrayList<Istoric> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        ArrayList<Istoric> listaIstoric=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Istoric ")){

            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()){
                    int idIstoric=result.getInt("idIstoric");
                    int idUser=result.getInt("idUser");
                    int idSpectacol=result.getInt("idSpectacol");
                    Spectacol spectacol=new Spectacol();
                    spectacol.setIdSpectacol(idSpectacol);
                    List<Spectacol> spetacole=new ArrayList<Spectacol>();
                    Istoric istoric=new Istoric(idIstoric,spetacole,idUser);
                    logger.traceExit(istoric);
                    listaIstoric.add(istoric);
                }
            }
        }
        catch (SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
        return listaIstoric;
    }
}
