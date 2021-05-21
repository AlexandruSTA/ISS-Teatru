package Teatrus.persistence.repository.jdbc;

import Teatrus.model.CardBancar;
import Teatrus.persistence.IRepositoryCardBancar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class RepositoryCardBancar implements IRepositoryCardBancar {
    private JDBCUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public RepositoryCardBancar(Properties props){
        logger.info("Initializing RepositoryCardBancar with properties: {} ",props);
        dbUtils=new JDBCUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from CardBancar")) {
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
    public void save(CardBancar entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into CardBancar(idCard,idUser,titular,numarCard,valabilitate) values (?,?,?,?,?)")){
            preStmt.setInt(1,size()+1);
            preStmt.setInt(2,entity.getIdUser());
            preStmt.setString(3, entity.getTitular());
            preStmt.setString(4,entity.getNumarCard());
            preStmt.setDate(5,(Date) entity.getValabilitate());
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
        try(PreparedStatement preStmt=con.prepareStatement("delete from CardBancar where idCard=?")){
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
    public void update(Integer integer, CardBancar entity) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update CardBancar set idUser=?, titular=?, numarCard=?, valabilitate=? where idCard=?")){
            preStmt.setInt(1,entity.getIdUser());
            preStmt.setString(2, entity.getTitular());
            preStmt.setString(3,entity.getNumarCard());
            preStmt.setDate(4,(Date) entity.getValabilitate());
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
    public CardBancar findOne(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from CardBancar where idCard=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    int idCard=result.getInt("idCard");
                    int idUser=result.getInt("idUser");
                    String titular=result.getString("titular");
                    String numarCard=result.getString("numarCard");
                    java.util.Date valabilitate=result.getTimestamp("valabilitate");
                    CardBancar cardBancar=new CardBancar(idCard,idUser,titular,numarCard,valabilitate);
                    logger.traceExit(cardBancar);
                    return cardBancar;
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
    public ArrayList<CardBancar> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        ArrayList<CardBancar> carduriBancare=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from CardBancar ")){

            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()){
                    int idCard=result.getInt("idCard");
                    int idUser=result.getInt("idUser");
                    String titular=result.getString("titular");
                    String numarCard=result.getString("numarCard");
                    java.util.Date valabilitate=result.getTimestamp("valabilitate");
                    CardBancar cardBancar=new CardBancar(idCard,idUser,titular,numarCard,valabilitate);
                    logger.traceExit(cardBancar);
                    carduriBancare.add(cardBancar);
                }
            }
        }
        catch (SQLException exc){
            logger.error(exc);
            System.out.println(exc);
        }
        logger.traceExit();
        return carduriBancare;
    }
}
